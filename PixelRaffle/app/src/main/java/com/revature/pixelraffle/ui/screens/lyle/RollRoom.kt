package com.revature.pixelraffle.ui.screens.lyle

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.*
import com.revature.pixelraffle.R
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
    val randomUsers= listOf("lyle lopez", "Adama Coulibaly", "Carlos Castellanos","Peter Lin")

    Scaffold(modifier = Modifier.fillMaxSize()) {

        SparklesA()

        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
            Sparkles()
        }

        Row(modifier = Modifier.fillMaxSize(),horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically) {

                Text(text = "CONGRATULATIONS: \n ${randomUsers.random()}!",fontWeight = FontWeight.Bold,fontSize = 20.sp)


        }


        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Bottom,horizontalAlignment = Alignment.End) {
            SparklesB()
        }


        //Image(painter = painterResource(id = R.drawable.mnbase_02), contentDescription = "",alpha = .13f,contentScale = ContentScale.FillBounds)


    }



}