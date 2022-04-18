@file:JvmName("RoomScreenKt")

package com.revature.pixelraffle.ui.screens.peter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.pixelraffle.data.Player
import com.revature.pixelraffle.ui.components.DrawBoard
import com.revature.pixelraffle.ui.components.MakeColorBar
import com.revature.pixelraffle.ui.navigation.NavScreens
import com.revature.pixelraffle.ui.theme.graySurface
import kotlin.math.roundToInt

/*
 Text(
                    text = buildAnnotatedString {
                        append("LOGIN")
                        addStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,
                            ),
                            start = 0,
                            end = 3
                        )
                        addStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            ),
                            start = 3,
                            end = 5
                        )
                    },
                    fontSize = 15.sp,
                )
                */


@Composable
fun RoomScreen(navController: NavController) {
//    FloatingActionButton(onClick = { /*TODO*/ },Modifier.size(120.dp,120.dp),shape = MaterialTheme.shapes.medium,backgroundColor = MaterialTheme.colors.primary) {
//        Text(text = "Create Room",color = Color.White)
//
//    }
    var listPlayer = Player.list

    var winner:Boolean by rememberSaveable { mutableStateOf(false) }
    var winMsg:String by rememberSaveable { mutableStateOf("") }

    var myColor = remember { mutableStateOf(Color.Red) }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.TopCenter
    )
    {


        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "Pixel Lotto",
                color = graySurface,
                modifier = Modifier
                    .fillMaxWidth()
                    //  .border(3.dp, Color.Red)
                    .padding(20.dp) ,
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )

            DrawBoard(myColor)
            MakeColorBar(myColor)
            Row(modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = {
//                        if(!winner) {
//                            winner = validation()
//                            if (winner) {
//                                winMsg = "Winner Winner Chicken Dinner"
//                            } else {
//                                winMsg = "No Winners"
//                            }
//                        }
                              navController.navigate(NavScreens.RollRoom.route)
                    },
                    modifier = Modifier.padding(15.dp).width(200.dp)
                ) {
                    if (!winner ) {
                        Text(text = "Roll!")
                    }
                    else
                        Text(winMsg, modifier = Modifier.padding(5.dp))

                }

                Button(
                    onClick = {
                        Player.list.clear()
                        Player.dragList.clear()

                    },
                    modifier = Modifier.padding(15.dp).width(200.dp)
                ) {
                    Text(text = "Reset!")
                }

//

            }

            Text(text= "Your Numbers", fontSize = 22.sp, modifier = Modifier.padding(5.dp))

            LazyColumn(){
                items(listPlayer){ it->
                    Box() {
                        var winMsg = ""
                        var x = it.offset.x.roundToInt()
                        var y = it.offset.y.roundToInt()
                        if(rnds_x.value == it.offset.x.roundToInt() && rnds_y.value == it.offset.y.roundToInt()) {
                            winMsg = "Winner Winner Chicken Dinner"
                        }
                        Text(text = "Pixel: [${x} , ${y}] $winMsg ")

                    }

                }

            }

        }
    }

}