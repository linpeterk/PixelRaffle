package com.example.pixelraffle.ui.screens.carlos

import android.graphics.*
import android.graphics.Matrix
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.util.Pools
import com.example.pixelraffle.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Stack
import kotlin.math.abs

@Composable
fun createRoom(modifier:Modifier,
               controller: SketchbookController,
               onEventListener: (bitmap: Bitmap) -> Unit) {

    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 20.dp),
            bitmap = ImageBitmap.imageResource(R.drawable.sketchbook_bg),
            contentDescription = null
        )

        Sketchbook(
            modifier = Modifier
                .matchParentSize()
                .padding(
                    start = 30.dp,
                    top = 40.dp,
                    end = 30.dp,
                    bottom = 20.dp
                ),
            controller = controller,
            backgroundColor = Color.White,
            onPathListener = {
                val bitmap = controller.getSketchbookBitmap()
                onEventListener.invoke(bitmap.asAndroidBitmap())
            }
        )
    }
}

@Composable
public fun Sketchbook(
    modifier: Modifier,
    controller: SketchbookController,
    backgroundColor: Color = Color.Transparent,
    imageBitmap: ImageBitmap? = null,
    onPathListener: ((path: Path) -> Unit)? = null,
    onEventListener: ((x: Float, y: Float, motionEvent: Int) -> Unit)? = null,
    onRevisedListener: ((canUndo: Boolean, canRedo: Boolean) -> Unit)? = null
) {
    var path = Path()
    var canvas: Canvas? = null
    val currentPoint = PointF(0f, 0f)
    val touchTolerance = LocalViewConfiguration.current.touchSlop
    val invalidatorTick: MutableState<Int> = remember { mutableStateOf(0) }

    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(key1 = controller) {
        coroutineScope.launch(Dispatchers.Main) {
            controller.setImageBitmap(imageBitmap)
            controller.setBackgroundColor(backgroundColor)
            controller.reviseTick.collect {
                canvas?.nativeCanvas?.drawColor(0, PorterDuff.Mode.CLEAR)
                val drawPaths = controller.drawPaths
                if (drawPaths.isNotEmpty()) {
                    drawPaths.forEach { sketchPath ->
                        canvas?.drawPath(sketchPath.path, sketchPath.paint)
                    }
                }
                invalidatorTick.value++
            }
        }

        onDispose {
            controller.releaseBitmap()
            controller.clear()
        }
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged { newSize ->
                val size =
                    newSize.takeIf { it.width != 0 && it.height != 0 } ?: return@onSizeChanged
                controller.releaseBitmap()
                controller.pathBitmap =
                    ImageBitmap(size.width, size.height, ImageBitmapConfig.Argb8888)
                        .also {
                            canvas = Canvas(it)
                            controller.bitmapSize.value = size
                        }
            }
            .pointerInteropFilter { event ->
                val motionTouchEventX = event.x
                val motionTouchEventY = event.y

                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        path.reset()
                        path.moveTo(motionTouchEventX, motionTouchEventY)
                        currentPoint.x = motionTouchEventX
                        currentPoint.y = motionTouchEventY
                    }
                    MotionEvent.ACTION_MOVE -> {
                        val dx = abs(motionTouchEventX - currentPoint.x)
                        val dy = abs(motionTouchEventY - currentPoint.y)
                        if (dx >= touchTolerance || dy >= touchTolerance) {
                            // QuadTo() adds a quadratic bezier from the last point,
                            // approaching control point (x1,y1), and ending at (x2,y2).
                            path.quadraticBezierTo(
                                currentPoint.x,
                                currentPoint.y,
                                (motionTouchEventX + currentPoint.x) / 2,
                                (motionTouchEventY + currentPoint.y) / 2
                            )
                            currentPoint.x = motionTouchEventX
                            currentPoint.y = motionTouchEventY

                            // Draw the path in the extra bitmap to save it.
                            if (controller.isEraseMode.value) {
                                canvas?.drawCircle(
                                    center = Offset(motionTouchEventX, motionTouchEventY),
                                    radius = controller.eraseRadius,
                                    paint = controller.currentPaint
                                )
                            } else {
                                canvas?.drawPath(path, controller.currentPaint)
                            }
                        }
                    }
                    MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                        controller.clearRedoPath()
                        controller.addDrawPath(path)
                        controller.updateRevised()
                        onPathListener?.invoke(path)
                        path = Path()
                    }
                    else -> false
                }
                onEventListener?.invoke(event.x, event.y, event.action)
                invalidatorTick.value++
                true
            }
    ) {
        drawIntoCanvas { canvas ->
            // draw image bitmap on the canvas.
            controller.imageBitmap?.let { imageBitmap ->
                var dx = 0f
                var dy = 0f
                val scale: Float
                val shaderMatrix = Matrix()
                val shader = ImageShader(imageBitmap, TileMode.Clamp)
                val brush = ShaderBrush(shader)
                val paint = paintPool.acquire() ?: Paint()
                paint.asFrameworkPaint().apply {
                    isAntiAlias = true
                    isDither = true
                    isFilterBitmap = true
                }

                // cache the paint in the internal stack.
                canvas.saveLayer(size.toRect(), paint)

                val mDrawableRect = RectF(0f, 0f, size.width, size.height)
                val bitmapWidth: Int = imageBitmap.asAndroidBitmap().width
                val bitmapHeight: Int = imageBitmap.asAndroidBitmap().height

                if (bitmapWidth * mDrawableRect.height() > mDrawableRect.width() * bitmapHeight) {
                    scale = mDrawableRect.height() / bitmapHeight.toFloat()
                    dx = (mDrawableRect.width() - bitmapWidth * scale) * 0.5f
                } else {
                    scale = mDrawableRect.width() / bitmapWidth.toFloat()
                    dy = (mDrawableRect.height() - bitmapHeight * scale) * 0.5f
                }

                // resize the matrix to scale by sx and sy.
                shaderMatrix.setScale(scale, scale)

                // post translate the matrix with the specified translation.
                shaderMatrix.postTranslate(
                    (dx + 0.5f) + mDrawableRect.left,
                    (dy + 0.5f) + mDrawableRect.top
                )
                // apply the scaled matrix to the shader.
                shader.setLocalMatrix(shaderMatrix)
                // Set the shader matrix to the controller.
                controller.imageBitmapMatrix.value = shaderMatrix
                // draw an image bitmap as a rect.
                drawRect(brush = brush, size = controller.bitmapSize.value.toSize())
                // restore canvas
                canvas.restore()
                // resets the paint and release to the pool.
                paint.asFrameworkPaint().reset()
                paintPool.release(paint)
            }

            // draw path bitmap on the canvas.
            controller.pathBitmap?.let { bitmap ->
                canvas.drawImage(bitmap, Offset.Zero, Paint())
            }
        }
        if (invalidatorTick.value != 0) {
            onRevisedListener?.invoke(controller.canUndo.value, controller.canRedo.value)
        }
    }
}

/** paint pool which caching and reusing [Paint] instances. */
private val paintPool = Pools.SimplePool<Paint>(2)


@Composable
public fun rememberSketchbookController(): SketchbookController {
    return remember { SketchbookController() }
}

/**
 * SketchbookController interacts with [Sketchbook] and it allows you to control the canvas and
 * all of the components with it.
 */
public class SketchbookController {

    /** A [Paint] to draws paths on canvas. */
    private val drawPaint: MutableState<Paint> = mutableStateOf(defaultPaint())

    /** A radius of the erase circle. */
    internal var eraseRadius: Float = 50f

    /** A [Paint] to erases paths on canvas, which properties are based on the [drawPaint]. */
    private val erasePaint: Paint = Paint().apply {
        shader = null
        color = backgroundColor
        style = PaintingStyle.Fill
        asFrameworkPaint().xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    /** The current [Paint] to draws paths on canvas. */
    public val currentPaint: Paint
        get() = if (isEraseMode.value) {
            erasePaint
        } else {
            drawPaint.value
        }

    private val _currentPaintColor: MutableState<Color> = mutableStateOf(drawPaint.value.color)

    /** [MutableState] of the current color of the [Paint]. */
    public val currentPaintColor: State<Color> = _currentPaintColor

    /** Background color to be used erasing colored paths. */
    private var backgroundColor: Color = Color.Transparent

    /** An [ImageBitmap] to draw paths on the canvas. */
    internal var pathBitmap: ImageBitmap? = null

    /** An [ImageBitmap] to draw a bitmap on the canvas as a background. */
    internal var imageBitmap: ImageBitmap? = null

    private val _selectedColorIndex: MutableState<Int> = mutableStateOf(initialSelectedIndex)

    /** State of the selected color index. */
    internal val selectedColorIndex: State<Int> = _selectedColorIndex

    private val _isEraseMode: MutableState<Boolean> = mutableStateOf(false)

    /** Indicates whether erase mode or not. */
    public val isEraseMode: State<Boolean> = _isEraseMode

    /** Stack of the drawn [Path]s. */
    internal val drawPaths = Stack<SketchPath>()

    /** Stack of the redo [Path]s. */
    private val redoPaths = Stack<SketchPath>()

    private val _canUndo: MutableState<Boolean> = mutableStateOf(false)

    /** Indicates can execute undo or not. */
    public val canUndo: State<Boolean> = _canUndo

    private val _canRedo: MutableState<Boolean> = mutableStateOf(false)

    /** Indicates can execute redo or not. */
    public val canRedo: State<Boolean> = _canRedo

    internal val bitmapSize: MutableState<IntSize> = mutableStateOf(IntSize(0, 0))

    internal val imageBitmapMatrix: MutableState<Matrix> = mutableStateOf(Matrix())

    internal var reviseTick = MutableStateFlow(0)

    /** Sets a background color. */
    public fun setBackgroundColor(color: Color) {
        backgroundColor = color
    }

    /** Sets an [ImageBitmap] to draw on the canvas as a background. */
    public fun setImageBitmap(bitmap: ImageBitmap?) {
        imageBitmap = bitmap
    }

    /** Sets an index of the selected color. */
    public fun setSelectedColorIndex(index: Int) {
        _selectedColorIndex.value = index
    }

    /** Sets a new [Paint]. */
    public fun setPaint(paint: Paint) {
        drawPaint.value = paint
        _currentPaintColor.value = paint.color
    }

    /** Sets an alpha to the [drawPaint]. */
    public fun setPaintAlpha(alpha: Float) {
        drawPaint.value.alpha = alpha
    }

    /** Sets a [Color] to the [drawPaint]. */
    public fun setPaintColor(color: Color) {
        drawPaint.value.color = color
        _currentPaintColor.value = color
    }

    /** Sets a stroke width to the [drawPaint]. */
    public fun setPaintStrokeWidth(strokeWidth: Float) {
        drawPaint.value.strokeWidth = strokeWidth
    }

    /** Sets a [Shader] to the [drawPaint]. */
    public fun setPaintShader(shader: Shader?) {
        drawPaint.value.shader = shader
    }

    /** Sets a color list as a linear shader to the paint. */
    public fun setLinearShader(colors: List<Color>) {
        val size = bitmapSize.value.takeIf { it.width != 0 && it.height != 0 } ?: return
        setPaintShader(
            LinearGradientShader(
                Offset(size.width.toFloat() / 2, 0f),
                Offset(size.width.toFloat() / 2, size.height.toFloat()),
                colors,
            )
        )
    }

    /** Sets a rainbow shader to the paint. */
    public fun setRainbowShader() {
        setLinearShader(defaultColorList())
        setSelectedColorIndex(initialSelectedIndex)
    }

    /** Sets a [PaintingStyle] to the [drawPaint]. */
    public fun setPaintingStyle(paintingStyle: PaintingStyle) {
        drawPaint.value.style = paintingStyle
    }

    /** Sets a [PathEffect] to the [drawPaint]. */
    public fun setPathEffect(pathEffect: PathEffect?) {
        drawPaint.value.pathEffect = pathEffect
    }

    /** Draws a [Path] with the current [Paint]. */
    public fun addDrawPath(path: Path) {
        drawPaths.add(SketchPath(path, Paint().copy(currentPaint)))
    }

    /** Draws a [Path] with the current [Paint]. */
    public fun addDrawPath(path: Path, paint: Paint) {
        drawPaths.add(SketchPath(path, Paint().copy(paint)))
    }

    internal fun clearRedoPath() {
        redoPaths.clear()
    }

    internal fun updateRevised() {
        _canUndo.value = drawPaths.isNotEmpty()
        _canRedo.value = redoPaths.isNotEmpty()
    }

    /** Executes undo the drawn path if possible. */
    public fun undo() {
        if (canUndo.value) {
            redoPaths.push(drawPaths.pop())
            reviseTick.value++
            updateRevised()
        }
    }

    /** Executes redo the drawn path if possible. */
    public fun redo() {
        if (canRedo.value) {
            drawPaths.push(redoPaths.pop())
            reviseTick.value++
            updateRevised()
        }
    }

    /**
     * Sets the erase mode or not.
     *
     * @param isEraseMode Flag to set erase mode.
     */
    public fun setEraseMode(isEraseMode: Boolean) {
        _isEraseMode.value = isEraseMode
    }

    /**
     * Sets the radius size of the erase circle.
     *
     * @param eraseRadius Radius of the erase circle.
     */
    public fun setEraseRadius(eraseRadius: Float) {
        this.eraseRadius = eraseRadius
    }

    /** Toggles the erase mode. */
    public fun toggleEraseMode() {
        _isEraseMode.value = !isEraseMode.value
    }

    /** Clear the drawn paths and redo paths on canvas.. */
    public fun clearPaths() {
        drawPaths.clear()
        redoPaths.clear()
        updateRevised()
        reviseTick.value++
    }

    /** Clear the image bitmap. */
    public fun clearImageBitmap() {
        setImageBitmap(null)
        imageBitmapMatrix.value = Matrix()
    }

    /** Clear drawn paths and the bitmap image. */
    public fun clear() {
        clearPaths()
        clearImageBitmap()
    }

    /** Returns the current [Sketchbook]'s bitmap. */
    public fun getSketchbookBitmap(): ImageBitmap {
        val size = bitmapSize.value
        val combinedBitmap = ImageBitmap(size.width, size.height, ImageBitmapConfig.Argb8888)
        val canvas = Canvas(combinedBitmap)
        imageBitmap?.let {
            val immutableBitmap = it.asAndroidBitmap().copy(Bitmap.Config.ARGB_8888, false)
            canvas.nativeCanvas.drawBitmap(immutableBitmap, imageBitmapMatrix.value, null)
            immutableBitmap.recycle()
        }
        pathBitmap?.let { canvas.drawImage(it, Offset.Zero, Paint()) }
        return combinedBitmap
    }

    internal fun releaseBitmap() {
        pathBitmap?.asAndroidBitmap()?.recycle()
        pathBitmap = null
    }
}