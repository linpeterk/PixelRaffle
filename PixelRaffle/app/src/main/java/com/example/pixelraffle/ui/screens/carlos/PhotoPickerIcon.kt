package com.example.pixelraffle.ui.screens.carlos

import android.annotation.SuppressLint
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
//import com.google.modernstorage.photopicker.PhotoPicker
//import io.getstream.sketchbook.SketchbookController
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.CallSuper
import androidx.core.os.BuildCompat
import java.util.ArrayList
import java.util.LinkedHashSet
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
//import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.IntSize
//import io.getstream.sketchbook.internal.SketchPath
//import io.getstream.sketchbook.internal.copy
//import io.getstream.sketchbook.internal.defaultPaint
//import io.getstream.sketchbook.internal.initialSelectedIndex
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Stack
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Paint
//import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import com.example.pixelraffle.R

//import androidx.compose.ui.graphics.Paint
//import androidx.compose.ui.graphics.Path

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun PhotoPickerIcon(
    controller: SketchbookController
) {
    val context = LocalContext.current
    val photoPicker =
        rememberLauncherForActivityResult(PhotoPicker()) { uris ->
            val uri = uris.firstOrNull() ?: return@rememberLauncherForActivityResult

            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
            } else {
                MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            }

            controller.setImageBitmap(bitmap.asImageBitmap())
        }

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)

    ) {
        val expanded = remember { mutableStateOf(false) }

        Image(
            modifier = Modifier
                .size(42.dp)
                .clickable { expanded.value = true },
            imageVector = ImageVector.vectorResource(R.drawable.ic_eraser_black_24dp),
            contentDescription = null
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    // Launch the picker with only one image selectable
                    photoPicker.launch(PhotoPicker.Args(PhotoPicker.Type.IMAGES_ONLY, 1))
                    expanded.value = false
                }
            ) {
                Text(text = "Select Photo")
            }

            DropdownMenuItem(
                onClick = {
                    controller.setImageBitmap(null)
                    expanded.value = false
                }
            ) {
                Text(text = "Clear Photo")
            }
        }
    }
}


@BuildCompat.PrereleaseSdkCheck
class PhotoPicker : ActivityResultContract<PhotoPicker.Args, List<Uri>>() {
    companion object {
        @JvmStatic
        fun isPhotoPickerAvailable(): Boolean {
            return BuildCompat.isAtLeastT()
        }

        private const val INTENT_PICK_IMAGES = "android.provider.action.PICK_IMAGES"
        private const val EXTRA_PICK_IMAGES_MAX = "android.provider.extra.PICK_IMAGES_MAX"
    }

    enum class Type {
        IMAGES_ONLY, VIDEO_ONLY, IMAGES_AND_VIDEO
    }

    class Args(val type: Type, val maxItems: Int)

    @CallSuper
    override fun createIntent(context: Context, input: Args): Intent {
        if (isPhotoPickerAvailable()) {
            val intent = Intent(INTENT_PICK_IMAGES).apply {
                if (input.maxItems > 1) {
                    putExtra(EXTRA_PICK_IMAGES_MAX, input.maxItems)
                }

                if (input.type == Type.IMAGES_ONLY) {
                    type = "image/*"
                } else if (input.type == Type.VIDEO_ONLY) {
                    type = "video/*"
                }
            }

            return intent
        } else {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                type = "*/*"

                if (input.maxItems > 1) {
                    putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                }

                when (input.type) {
                    Type.IMAGES_ONLY ->
                        putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*"))
                    Type.VIDEO_ONLY ->
                        putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("video/*"))
                    Type.IMAGES_AND_VIDEO ->
                        putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/*", "video/*"))
                }
            }

            return intent
        }
    }

    override fun getSynchronousResult(
        context: Context,
        input: Args
    ): SynchronousResult<List<Uri>>? {
        return null
    }

    override fun parseResult(resultCode: Int, intent: Intent?): List<Uri> {
        return if (resultCode != Activity.RESULT_OK || intent == null) emptyList() else getClipDataUris(
            intent
        )
    }

    private fun getClipDataUris(intent: Intent): List<Uri> {
        // Use a LinkedHashSet to maintain any ordering that may be
        // present in the ClipData
        val resultSet = LinkedHashSet<Uri>()
        if (intent.data != null) {
            resultSet.add(intent.data!!)
        }
        val clipData = intent.clipData
        if (clipData == null && resultSet.isEmpty()) {
            return emptyList()
        } else if (clipData != null) {
            for (i in 0 until clipData.itemCount) {
                val uri = clipData.getItemAt(i).uri
                if (uri != null) {
                    resultSet.add(uri)
                }
            }
        }
        return ArrayList(resultSet)
    }
}


/** Creates and remembers a [SketchbookController] on the current composer. */
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
    internal val initialSelectedIndex: Int = -1
    private val _selectedColorIndex: MutableState<Int> = mutableStateOf(initialSelectedIndex)


    /** State of the selected color index. */
    internal val selectedColorIndex: State<Int> = _selectedColorIndex

    private val _isEraseMode: MutableState<Boolean> = mutableStateOf(false)

    /** Indicates whether erase mode or not. */
    public val isEraseMode: State<Boolean> = _isEraseMode

    /** Wrapper class to contain [Path] and [Paint]. */
    internal data class SketchPath(
        val path: Path,
        val paint: Paint
    )

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

/** Copy the compose properties of a [Paint] and apply it to other. */
internal fun Paint.copy(from: Paint): Paint = apply {
    alpha = from.alpha
    isAntiAlias = from.isAntiAlias
    color = from.color
    blendMode = from.blendMode
    style = from.style
    strokeWidth = from.strokeWidth
    strokeCap = from.strokeCap
    strokeJoin = from.strokeJoin
    strokeMiterLimit = from.strokeMiterLimit
    filterQuality = from.filterQuality
    shader = from.shader
    colorFilter = from.colorFilter
    pathEffect = from.pathEffect
}

/** Returns a default [Paint]. */
internal fun defaultPaint(): Paint {
    return Paint().apply {
        color = Color.White
        strokeWidth = 14f
        isAntiAlias = true
        style = PaintingStyle.Stroke
        strokeJoin = StrokeJoin.Round
        strokeCap = StrokeCap.Round
    }
}

internal fun defaultColorList(): List<Color> {
    return listOf(
        Color(0xFFf8130d),
        Color(0xFFb8070d),
        Color(0xFF7a000b),
        Color(0xFFff7900),
        Color(0xFFfff8b3),
        Color(0xFFfcf721),
        Color(0xFFf8df09),
        Color(0xFF8a3a00),
        Color(0xFFc0dc18),
        Color(0xFF88dd20),
        Color(0xFF07ddc3),
        Color(0xFF01a0a3),
        Color(0xFF59cbf0),
        Color(0xFF005FFF),
        Color(0xFF022b6d),
        Color(0xFFfa64e1),
        Color(0xFFfc50a6),
        Color(0xFFd7036a),
        Color(0xFFdb94fe),
        Color(0xFFb035f8),
        Color(0xFF7b2bec),
        Color(0xFFb0aaae),
        Color(0xFF768484),
        Color(0xFF333333),
        Color(0xFF0a0c0b),
    )
}