package com.example.pixelraffle.ui.screens.lyle

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.example.pixelraffle.ui.components.Logo
import com.example.pixelraffle.ui.theme.PixelRaffleTheme
import com.example.pixelraffle.ui.theme.PressStart


@Preview
@Composable
fun previewTest(){
    MainOverallScreen ()
}
@Preview
@Composable
fun darkpreviewTest(){
    PixelRaffleTheme(darkTheme = true) {
        MainOverallScreen ()
    }

}



@Composable
fun MainOverallScreen (/*navController: NavController*/){

    Surface(modifier = Modifier.fillMaxSize()/*,color= Color.White*/) {

        Image(painter = painterResource(id = com.example.pixelraffle.R.drawable.mnbase_02), contentDescription = "",alpha = .25f,contentScale = ContentScale.FillBounds)


        Logo()

        Column(modifier = Modifier
            .fillMaxSize()
            .offset(y = 90.dp)/*.border(2.dp,color= Color.Red)*/
            .padding(4.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "ADDING THAT LITTLE EXTRA TO YOUR EVENT!",textAlign = TextAlign.Center, modifier = Modifier
                .padding(2.dp)
                .width(220.dp),color= Color.Black,fontFamily = PressStart,fontSize = 18.sp )

            Button(onClick = { /*navController.navigate(DrawerScreens.MainUser.route)*/ },modifier= Modifier
                .padding(4.dp)
                .width(240.dp)/*.offset(x=-18.dp,y=-50.dp)*/) {
                Text(text = "Log In", modifier = Modifier.padding(2.dp),color= Color.White)

            }
            Button(onClick = { /*navController.navigate(DrawerScreens.MainLawyer.route)*/ },modifier= Modifier
                .padding(4.dp)
                .width(240.dp)/*.offset(x=-18.dp,y=-50.dp)*/) {
                Text(text = "Register", modifier = Modifier.padding(2.dp),color=Color.White)

            }

        }

    }

}