package com.example.pixelraffle.ui.screens.adama

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pixelraffle.viewmodel.UserViewModel
import com.example.pixelraffle.R



//New User Register Page
@Composable
fun RegisterPage(navController: NavController, userViewModel: UserViewModel){
    val context = LocalContext.current

    //User First Name variable
    val firstName = rememberSaveable{ mutableStateOf("")}

    //User First Last Name variable
    val lastName = rememberSaveable{ mutableStateOf("")}

    //User Email Address variable
    val email = rememberSaveable{ mutableStateOf("")}

    //User Password variable
    val Password = rememberSaveable{ mutableStateOf("")}

    //User Address variable
    val Adrress = rememberSaveable{ mutableStateOf("")}

    //User User ZipCode variable
    val ZipCode = rememberSaveable{ mutableStateOf("")}

    //The password eye
    val passwordVisbility = rememberSaveable{ mutableStateOf("false")}

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
            .padding(20.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 2.dp, top = 0.dp, end = 2.dp, bottom = 10.dp)
            ){
                Image( 
                    painterResource(id = R.drawable.pixelrafflelogo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                        .background(colorResource(id = R.color.white))
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clickable Logo",
                            onClick = {
                                Toast.makeText(context,"Welcome to Pixel Raffle", Toast.LENGTH_LONG).show()
                            }
                        )
                )
        }
    }



}
@Preview
@Composable
fun RegisterPagePreview(){

}

//User Login Page
@Composable
fun LoginPage(){

}

//User's old activities page
@Composable
fun UserActivityHistoryPage(){

}
