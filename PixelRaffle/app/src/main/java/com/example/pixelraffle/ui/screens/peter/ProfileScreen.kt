package com.example.pixelraffle.ui.screens.peter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp


@Composable
fun ProfileScreen(){

    Surface(color= MaterialTheme.colors.background){

        Box(
            modifier= Modifier
                .fillMaxSize()
                .drawBehind {
                    val path = Path()
                    val x = size.width
                    val y = size.height
                    val center = size.center
                    path.apply {
                        moveTo(0f, 0f)
                        lineTo(x, 0f)
                        lineTo(x, center.y / 2)
                        cubicTo(
                            x1 = 3 * x / 4,
                            y1 = center.y * 2 / 6,
                            x2 = x / 4,
                            y2 = center.y * 2 / 6,
                            x3 = 0f,
                            y3 = center.y / 2
                        )
                    }
                    drawPath(path = path, color = Color.Red

                    )

                }
        ){

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)

            ) {
                Text("CenterTest1")
                Text("CenterTest2")
            }

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .wrapContentSize(align = Alignment.BottomCenter)) {

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(text = "Bottom Test")
                }
            }

        }

    }

}