package com.revature.pixelraffle.ui.screens.lyle

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.revature.pixelraffle.R
import com.revature.pixelraffle.ui.navigation.NavScreens
import com.revature.pixelraffle.ui.screens.peter.validation
import com.revature.pixelraffle.viewmodel.UserViewModel


//////////////////////////  ANIMATION ////////////////////////////////
@Composable
fun Sparkles(){

    val compositionResult: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(com.revature.pixelraffle.R.raw.pixelburst))

    val progress by animateLottieCompositionAsState(
        compositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = .5f)

    LottieAnimation(compositionResult.value, progress)

}

//////////////////////////  ANIMATION ////////////////////////////////
@Composable
fun SparklesA(){

    val compositionResult: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(com.revature.pixelraffle.R.raw.pixelburst))

    val progress by animateLottieCompositionAsState(
        compositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = .35f)

    LottieAnimation(compositionResult.value, progress)

}

//////////////////////////  ANIMATION ////////////////////////////////
@Composable
fun SparklesB(){

    val compositionResult: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(com.revature.pixelraffle.R.raw.pixelburst))

    val progress by animateLottieCompositionAsState(
        compositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = .2f)

    LottieAnimation(compositionResult.value, progress)

}


@Composable
fun RollRoom(navController: NavController){

//    val user= userViewModel.getAllUserLis().observeAsState(arrayListOf())
//    val listHolder = user.value
    val randomUsers= listOf("Lyle Lopez", "Adama Coulibaly", "Peter Lin","Carlos Castellanos")

    Scaffold(modifier = Modifier.fillMaxSize()) {


        FloatingActionButton(onClick = {navController.navigate(NavScreens.Room.route)}, modifier = Modifier.padding(5.dp), backgroundColor = MaterialTheme.colors.primary) {
            Icon(imageVector = Icons.Default.ArrowBack , contentDescription ="")
        }



        SparklesA()

        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
            Sparkles()
        }

        Row(modifier = Modifier.fillMaxSize(),horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically) {

                Text(text = "CONGRATULATIONS: \n  ${randomUsers.random()}!",fontWeight = FontWeight.Bold,fontSize = 20.sp)


        }


        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Bottom,horizontalAlignment = Alignment.End) {
            SparklesB()
        }


        //Image(painter = painterResource(id = R.drawable.mnbase_02), contentDescription = "",alpha = .13f,contentScale = ContentScale.FillBounds)


    }



}