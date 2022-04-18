package com.revature.pixelraffle.ui.screens.peter

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.revature.pixelraffle.ui.screens.adama.UserProfileImage
import com.revature.pixelraffle.ui.theme.orange_2


@Composable
fun ProfileScreen(navController: NavController){

    Surface(color= MaterialTheme.colors.background){
     //   Image(painter = painterResource(id = R.drawable.mnbase_02), contentDescription = "",alpha = .18f,contentScale = ContentScale.FillBounds)
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
                            y1 = center.y * 4 / 7,
                            x2 = x / 4,
                            y2 = center.y * 4 / 7,
                            x3 = 0f,
                            y3 = center.y / 2
                        )
                    }
                    drawPath(
                        path = path, color = orange_2

                    )

                }
        ){

            //Content at top
            Column(modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {


                UserProfileImage()
                Text(
                    text = "UserName",
                    style= MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center

                )
                Spacer(Modifier.padding(20.dp))
                Text("CenterTest1")
                Text("CenterTest2")
            }

            //Content in middle
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)

            ) {
                Text("CenterTest1")
                Text("CenterTest2")
            }

            //Content in Bottom
//            Column(modifier = Modifier
//                .fillMaxSize()
//                .padding(20.dp)
//                .wrapContentSize(align = Alignment.BottomCenter)) {
//
//                Button(
//                    onClick = { /*TODO*/ },
//                    modifier = Modifier.fillMaxWidth(),
//                    shape = MaterialTheme.shapes.medium
//                ) {
//                    Text(text = "Bottom Test")
//                }
//            }

        }

    }

}

