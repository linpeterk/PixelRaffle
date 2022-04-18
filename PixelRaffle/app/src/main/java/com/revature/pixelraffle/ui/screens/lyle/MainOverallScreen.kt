package com.revature.pixelraffle.ui.screens.lyle

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.pixelraffle.ui.components.Logo
import com.revature.pixelraffle.ui.navigation.NavScreens
import com.revature.pixelraffle.ui.theme.PressStart


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


