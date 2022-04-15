package com.example.pixelraffle.ui.screens.lyle

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pixelraffle.R
import com.example.pixelraffle.ui.theme.*


@Preview
@Composable
fun previewMainMenu(){
    PixelRaffleTheme() {
        MainMenuScreen()
    }

}


@Composable
fun MainMenuScreen(){

    Scaffold(modifier = Modifier.fillMaxSize()) {

        Surface(modifier = Modifier.fillMaxSize()) {
            Image(painter = painterResource(id = R.drawable.mnbase_02), contentDescription = "",alpha = .13f,contentScale = ContentScale.FillBounds)


            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(y = 80.dp),horizontalArrangement = Arrangement.Center) {
                Text(text = "WELCOME:   .",textAlign = TextAlign.Center,fontFamily = FontFamily.Default,fontSize = 25.sp,fontWeight = FontWeight.Bold)
            }
            
            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(y = -150.dp),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceEvenly) {
                
                FloatingActionButton(onClick = { /*TODO*/ },Modifier.size(120.dp,120.dp),shape = MaterialTheme.shapes.medium,backgroundColor = MaterialTheme.colors.primary) {
                    Text(text = "Create Room",color = Color.White)
                    
                }

                FloatingActionButton(onClick = { /*TODO*/ },Modifier.size(120.dp,120.dp),shape = MaterialTheme.shapes.medium,backgroundColor = MaterialTheme.colors.primary) {
                    Text(text = "Join Room",color = Color.White)
                }
                
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(y = 70.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Create Raffles to help raise funds for your event.",
                        modifier = Modifier.width(325.dp),textAlign = TextAlign.Center,fontFamily = /*PressStart*/FontFamily.Default,fontWeight = FontWeight.Bold,fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(100.dp))
                    Text(text = "Pixel Raffle is not liable for loss due to miss allocation of prizes or disputes pertaining to such matters. Please play fairly.",
                        modifier = Modifier.width(300.dp),textAlign = TextAlign.Justify,fontFamily = FontFamily.Default,fontWeight = FontWeight.Light,fontSize = 12.sp)
                }


            }
            

            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(y = -100.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceEvenly) {

                //Google icon
                FloatingActionButton(onClick = { /*TODO*/ },
                    shape = CircleShape,
                    backgroundColor = Color.White,
                    modifier = Modifier.size(70.dp,70.dp)) {

                    Image(painter = painterResource(id = R.drawable.facebook), contentDescription ="facebook",Modifier.scale(.6f) )


                }

                //Twitter icon
                FloatingActionButton(onClick = { /*TODO*/ },
                    shape = CircleShape,
                    backgroundColor = Color.White,
                    modifier = Modifier.size(70.dp,70.dp)) {

                    Image(painter = painterResource(id = R.drawable.twitter), contentDescription ="twitter",Modifier.scale(.6f) )


                }

                //
                FloatingActionButton(onClick = { /*TODO*/ },
                    shape = CircleShape,
                    backgroundColor = Color.White,
                    modifier = Modifier.size(70.dp,70.dp),) {

                    Image(painter = painterResource(id = R.drawable.instagram), contentDescription ="instagram",Modifier.scale(.6f) )

                }


            }




        }

    }

}