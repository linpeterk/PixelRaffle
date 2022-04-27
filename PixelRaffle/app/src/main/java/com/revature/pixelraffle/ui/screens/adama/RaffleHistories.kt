package com.revature.pixelraffle.ui.screens.adama

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.listviewcustomizedv1.ui.theme.raffles
import com.revature.pixelraffle.R
import com.revature.pixelraffle.database.datamodel.Raffle
import com.revature.pixelraffle.database.datamodel.RaffleCard
import com.revature.pixelraffle.ui.navigation.BottomNavigationBar
import com.revature.pixelraffle.ui.theme.PixelRaffleTheme
import com.revature.pixelraffle.ui.theme.orange_2
import com.revature.pixelraffle.viewmodel.UserViewModel

@Preview(

    uiMode=UI_MODE_NIGHT_YES,
    name="Dark Mode"

)
@Preview(

    uiMode=UI_MODE_NIGHT_NO,
    name="Light Mode"

)
@Composable
fun defaultPreview()
{
    PixelRaffleTheme {

        //RaffleHistories(raffles,)
    }

}


@Composable
fun RaffleHistories(raffleList:List<Raffle>, navController: NavController, userViewModel: UserViewModel)
{
    Scaffold(modifier=Modifier.fillMaxSize(),
        bottomBar= { BottomNavigationBar(navController) },

//        topBar = {
//            //TopAppBar(backgroundColor = MaterialTheme.colors.primary,
//            //title = { Text(text = "USER RAFFLE PAGE")})
//            Row(modifier = Modifier.padding(bottom = 5.dp)) {
//                GetProfileImage(userViewModel)
//                //UserProfilePage()
//            }
//        }
    )
    {
        Column(modifier = Modifier
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

            }) {
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

            Row(modifier = Modifier.fillMaxWidth(),Arrangement.Center) {
                Text(text = "Raffle History",fontSize = 20.sp,fontWeight = FontWeight.Bold)
            }

            LazyColumn(
                modifier= Modifier
                    .fillMaxWidth()
                    .offset(y = 20.dp),
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


    }

}








