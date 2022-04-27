package com.revature.pixelraffle.ui.screens.peter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.pixelraffle.database.datamodel.CurrentRaffle
import com.revature.pixelraffle.database.datamodel.Raffle
import com.revature.pixelraffle.database.datamodel.RaffleCard
import com.revature.pixelraffle.ui.navigation.BottomNavigationBar
import com.revature.pixelraffle.ui.screens.adama.UserProfileImage
import com.revature.pixelraffle.ui.theme.PressStart
import com.revature.pixelraffle.ui.theme.orange_2
import com.revature.pixelraffle.viewmodel.UserViewModel


@Composable
fun ProfileScreen(raffleList:List<CurrentRaffle>,navController: NavController, userViewModel: UserViewModel){

    Scaffold(bottomBar = { BottomNavigationBar(navController) },/*color= MaterialTheme.colors.background*/){
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
                    text = "${userViewModel.currentUser.first_name} ${userViewModel.currentUser.last_name}",
                    style= MaterialTheme.typography.h5,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center

                )
                Spacer(Modifier.padding(20.dp))
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                    Text(text = "Email: ", fontWeight = FontWeight.Bold)
                    Text(text="${userViewModel.currentUser.email}")
                }
                Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center) {
                    Text(text = "Password: ", fontWeight = FontWeight.Bold)
                    Text(text="${userViewModel.currentUser.password}")
                }


            }



            //Content in middle
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .offset(y = 295.dp)

            ) {
                LazyColumn(

                    Modifier.fillMaxWidth(),
                    contentPadding= PaddingValues(start = 16.dp, top = 5.dp, end = 16.dp, bottom = 16.dp)

                )
                {

                    item {

                        Row(
                            modifier= Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(vertical = 0.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically

                        ) {

                            // Text(text = "RAFFLE HISTORIES",
                            //style= MaterialTheme.typography.h5 )
                        }

                    }// end of item

                    items(raffleList)
                    { myRaffle->

                        RaffleCard(myRaffle.name, myRaffle.ticketNumber,myRaffle.thedate,myRaffle.boardImageRes)

                    }



                }
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

