package com.example.pixelraffle.ui.screens.lyle

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pixelraffle.ui.components.Logo
import com.example.pixelraffle.ui.components.LogoA
import com.example.pixelraffle.ui.navigation.NavScreens
import com.example.pixelraffle.ui.theme.PixelRaffleTheme
import com.example.pixelraffle.ui.theme.PressStart
import com.example.pixelraffle.ui.theme.blue_2


//@Preview
//@Composable
//fun previewTest(){
//    MainOverallScreen ()
//}
//@Preview
//@Composable
//fun darkpreviewTest(){
//    PixelRaffleTheme(darkTheme = true) {
//        MainOverallScreen ()
//    }
//
//}



@Composable
fun MainOverallScreen (navController: NavController){

    Scaffold(modifier = Modifier.fillMaxSize()/*,color= Color.White*/) {

      //  Image(painter = painterResource(id = com.example.pixelraffle.R.drawable.mnbase_02), contentDescription = "",alpha = .25f,contentScale = ContentScale.FillBounds)


        Logo()

        Column(modifier = Modifier
            .fillMaxSize()
            .offset(y = 200.dp)/*.border(2.dp,color= Color.Red)*/
            .padding(4.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "ADDING THAT LITTLE EXTRA TO YOUR EVENT!",textAlign = TextAlign.Center, modifier = Modifier
                .padding(2.dp)
                .width(220.dp),color= Color.Black,fontFamily = PressStart,fontSize = 18.sp )

            Button(onClick = {  navController.navigate(NavScreens.Login.route) },modifier= Modifier
                .padding(4.dp)
                .width(300.dp), shape = CircleShape) {
                Text(text = "Log In", modifier = Modifier.padding(2.dp),color= Color.White)

            }
            Button(onClick = {  navController.navigate(NavScreens.Register.route) },modifier= Modifier
                .padding(4.dp)
                .width(300.dp),shape = CircleShape,/*border = BorderStroke(2.dp,color = Color.White)*/
            ) {
                Text(text = "Register", modifier = Modifier.padding(2.dp),color=Color.White)

            }

        }

    }

}


