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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.listviewcustomizedv1.ui.theme.raffles
import com.revature.pixelraffle.R
import com.revature.pixelraffle.database.datamodel.Raffle
import com.revature.pixelraffle.database.datamodel.RaffleCard
import com.revature.pixelraffle.ui.theme.PixelRaffleTheme
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
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(

            topBar = {
                //TopAppBar(backgroundColor = MaterialTheme.colors.primary,
                    //title = { Text(text = "USER RAFFLE PAGE")})
                Row(modifier = Modifier.padding(bottom = 5.dp)) {
                    GetProfileImage(userViewModel)
                }

            }
        )
        {
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
    }

}








