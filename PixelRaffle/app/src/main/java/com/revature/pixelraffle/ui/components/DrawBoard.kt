package com.revature.pixelraffle.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.revature.pixelraffle.data.OffsetColor
import com.revature.pixelraffle.data.Player

@Composable
fun DrawBoard(myColor: MutableState<Color>){

    val theDp = with(LocalDensity.current) {
        1000.toDp()
    }



    Card(
        elevation = 9.dp,
        // shape = RectangleShape,
        backgroundColor = Color.Gray,
        modifier = Modifier
            //  .border(3.dp, Color.Blue)
            .requiredSize(theDp)
            .background(Color.White)
            .border(1.dp, Color.Gray)
            .pointerInput(Unit) {

                detectTapGestures(onPress = { offset ->

                    Player.list.add(OffsetColor(offset, myColor.value))


                    //currently not used  Player.maps[pixel_x.toString() + pixel_y.toString()] = true

                    Log.d("PixelPlus", "PixelPlus=${offset.toString() + offset.toString()}")
                    Log.d("Pixel", "Pixel=$offset")

                    detectDragGestures { change, dragAmount ->
                        Log.d("Pixel", "Pixel=$change")

                        Player.dragList.add(OffsetColor(change.position, myColor.value))


                    }

                })

            }
            .drawWithContent {


                Player.list.forEach {
//                    drawCircle(
//                        color = it.color,
//                        radius = 5f,
//                    center = it.offset
//                    )
                    drawRect(color = it.color, topLeft= it.offset,size = Size(40.0f,40.0f)  )



                }
//                drawPoints(
//                points = Player.list[0],
//                pointMode = PointMode.Points,
//                color=myColor.value,
//                        strokeWidth = 10f
//            )
            } // Show points or drag by changing Player.List

    )
    {
        Text(
            text = "", modifier = Modifier
            //     .background(graySurface)
            //   .border(4.dp, Color.Red)
        )

    }
}