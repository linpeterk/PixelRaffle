@file:JvmName("RoomScreenKt")

package com.revature.pixelraffle.ui.screens.peter

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.revature.pixelraffle.data.Player
import com.revature.pixelraffle.ui.components.DrawBoard
import com.revature.pixelraffle.ui.components.MakeColorBar
import com.revature.pixelraffle.ui.navigation.BottomNavigationBar
import com.revature.pixelraffle.ui.navigation.NavScreens
import com.revature.pixelraffle.ui.theme.graySurface
import com.revature.pixelraffle.viewmodel.UserViewModel
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
fun RoomScreen(navController: NavController, vModel:UserViewModel) {
//    FloatingActionButton(onClick = { /*TODO*/ },Modifier.size(120.dp,120.dp),shape = MaterialTheme.shapes.medium,backgroundColor = MaterialTheme.colors.primary) {
//        Text(text = "Create Room",color = Color.White)
//
//    }
    vModel.getBoard()

    var listPlayer = Player.list
    var dragListPlayer = Player.dragList

    var winner:Boolean by rememberSaveable { mutableStateOf(false) }
    var winMsg:String by rememberSaveable { mutableStateOf("") }

    var myColor = remember { mutableStateOf(Color.Red) }
Scaffold( modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController = navController)}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    )
    {


        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = buildAnnotatedString {
                    append("Pixel Raffle")
                    addStyle(
                        style = SpanStyle(
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                        ),
                        start = 0,
                        end = 5
                    )
                    addStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                        start = 5,
                        end = 12
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    //  .border(3.dp, Color.Red)
                    .padding(20.dp),
                textAlign = TextAlign.Center,
                fontSize = 30.sp
            )

            DrawBoard(myColor, vModel)
            MakeColorBar(myColor)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.SpaceEvenly) {
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
                    modifier = Modifier
                        .padding(15.dp)
                        .size(width=150.dp, height = 40.dp)
                ) {
                    if (!winner) {
                        Text(text = "End Event!")
                    } else
                        Text(winMsg, modifier = Modifier.padding(5.dp))

                }

                Button(
                    onClick = {
//                        Player.list.clear()
//                        Player.dragList.clear()
                        dragListPlayer.forEach {

                            //   Log.d("Player", "${it.offset}")
                        }
                        val json = Gson().toJson(dragListPlayer)
                        Log.d("Player", "${json}")

                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .size(width=150.dp, height = 40.dp)
                ) {
                    Text(text = "Submit!")
                }

//

            }

            Text(text = "Your Numbers", fontSize = 22.sp, modifier = Modifier.padding(5.dp))

            LazyColumn() {

                items(dragListPlayer) { it ->
                    Box() {
                        var winMsg = ""
                        var x = it.offset.x.roundToInt()
                        var y = it.offset.y.roundToInt()
                        if (rnds_x.value == it.offset.x.roundToInt() && rnds_y.value == it.offset.y.roundToInt()) {
                            winMsg = "Winner Winner Chicken Dinner"
                        }

                        Text(text = "Pixel: [${x} , ${y}] $winMsg ")

                    }

                }


            }

        }
     }
    }

}