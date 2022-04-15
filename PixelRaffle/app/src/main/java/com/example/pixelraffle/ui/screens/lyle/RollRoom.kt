package com.example.pixelraffle.ui.screens.lyle

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.example.pixelraffle.R


//////////////////////////  ANIMATION ////////////////////////////////
@Composable
fun Sparkles(){

    val compositionResult: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(com.example.pixelraffle.R.raw.pixelburst))

    val progress by animateLottieCompositionAsState(
        compositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = .5f)

    LottieAnimation(compositionResult.value, progress)

}


@Composable
fun RollRoom(){

    Scaffold(modifier = Modifier.fillMaxSize()) {

        Image(painter = painterResource(id = R.drawable.mnbase_02), contentDescription = "",alpha = .13f,contentScale = ContentScale.FillBounds)

        Sparkles()

        Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center,verticalAlignment = Alignment.CenterVertically) {

            Text(text = "CONGRATULATIONS:    !",fontWeight = FontWeight.Bold,fontSize = 20.sp)

        }

    }



}