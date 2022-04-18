package com.revature.pixelraffle.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.revature.pixelraffle.R


//////////////////////////////  LOGO (LIGHT & DARK) FOR SCREENS ////////////////////////////////////
@Composable
fun Logo(){

    Box(modifier=Modifier/*.border(2.dp,color=Color.Black)*/.fillMaxSize(),contentAlignment = Alignment.TopCenter) {
        if (isSystemInDarkTheme()){
            Image(painter = painterResource(id = R.drawable.pixel_logob_03), contentDescription = "",alignment = Alignment.TopCenter,
                modifier = Modifier
                    .scale(1.2f)
                    /*.width(180.dp)*/
                    .offset(y = 48.dp)
            )
        }else{
            Image(painter = painterResource(id = R.drawable.pixel_logoa_02), contentDescription = "",alignment = Alignment.TopCenter,
                modifier = Modifier
                    .scale(1.2f)
                    /*.width(180.dp)*/
                    .offset(y = 48.dp)
            )
        }

    }

}

//////////////////////////////  LOGO FOR SCREENS ////////////////////////////////////
@Composable
fun LogoA(){

    Box(modifier=Modifier/*.border(2.dp,color=Color.Black)*/.fillMaxSize(),contentAlignment = Alignment.TopCenter) {

            Image(painter = painterResource(id = R.drawable.pixel_logoa_02), contentDescription = "",alignment = Alignment.TopCenter,
                modifier = Modifier
                    .scale(1.2f)
                    /*.width(180.dp)*/
                    .offset(y = 48.dp))
    }

}