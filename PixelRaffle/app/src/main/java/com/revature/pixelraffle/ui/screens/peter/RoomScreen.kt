@file:JvmName("RoomScreenKt")

package com.revature.pixelraffle.ui.screens.peter

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
    val context= LocalContext.current
    vModel.getBoard()
    var selectedIndex = remember { mutableStateOf(0) }
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

            DrawBoard(myColor, vModel, selectedIndex)
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
//                        Player.list.clear()
//                        Player.dragList.clear()
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
                    //    Log.d("Player", "${json}")


                        val maxLogSize = 1000
                        json.chunked(maxLogSize).forEach { Log.d("Player", it) }

                    Toast.makeText(context, "Pixels submitted", Toast.LENGTH_LONG).show()
                    },
                    modifier = Modifier
                        .padding(15.dp)
                        .size(width=150.dp, height = 40.dp)
                ) {
                    Text(text = "Submit!")
                }

//

            }
            Card(elevation = 2.dp) {
                playerDropDownMenu(vModel = vModel, selectedIndex)
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

fun logUnlimited(tag: String, string: String) {
    val maxLogSize = 1000
    string.chunked(maxLogSize).forEach { Log.v(tag, it) }
}

@Composable
fun playerDropDownMenu(vModel: UserViewModel, selectedIndex:MutableState<Int>){
    var expanded by remember { mutableStateOf(false) }
    val sortItems = mutableListOf<String>("All", "You")
    vModel.Board.yourBoard?.forEach { sortItems.add(it.name) }

    Box(modifier = Modifier) {
        Text(sortItems[selectedIndex.value],modifier = Modifier
            .width(130.dp)
            .padding(3.dp)
            .clickable(onClick = { expanded = true })
            .background(
                Color.White
            ),
            textAlign = TextAlign.Center
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(
                Color.White)
        ) {
            sortItems.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex.value = index
                    expanded = false

                    vModel.sortBy(index) //index determines which sorting methods


                }) {
                    Text(text = s )
                }
            }
        }
    }

}