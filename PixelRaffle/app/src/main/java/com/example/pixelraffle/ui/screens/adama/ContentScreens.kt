package com.example.pixelraffle.ui.screens.adama

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pixelraffle.viewmodel.UserViewModel
import com.example.pixelraffle.R
import com.example.pixelraffle.ui.theme.graySurface


//New User Register Page//
//fun RegisterPage(navController: NavController, userViewModel: UserViewModel)
@Composable
fun RegisterPage(){
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
                        .padding(start = 2.dp, top = 0.dp, end = 2.dp, bottom = 20.dp)
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
        Column(modifier = Modifier
            .height(20.dp)
            .fillMaxWidth()
            .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text="Pixel Raffle", color = graySurface, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.padding(5.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()){
            //User Fisrt Name
            OutlinedTextField(value = firstName.value, onValueChange = {firstName.value=it},
                label = {Text(text="First Name", color = Color.Black, style = TextStyle(letterSpacing = TextUnit.Unspecified),
                    fontSize = TextUnit.Unspecified)},

                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f))

            //User Fisrt Name
            OutlinedTextField(value = lastName.value, onValueChange = {lastName.value=it},
                label = {Text(text="Last Name", color = Color.Black, style = TextStyle(letterSpacing = TextUnit.Unspecified),
                    fontSize = TextUnit.Unspecified)},

                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f))

            //Email Addres textfield field
            OutlinedTextField(value = email.value, onValueChange = {email.value=it},
                label = {Text(text="Email Address", color = Color.Black, style = TextStyle(letterSpacing = TextUnit.Unspecified),
                    fontSize = TextUnit.Unspecified)},

                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.8f))

        }
    }



}

//User Login Page
@Composable
fun LoginPage(){

}

//User's old activities page
@Composable
fun UserActivityHistoryPage(){

}
