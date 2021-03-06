package com.revature.pixelraffle.ui.screens.lyle

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.pixelraffle.R
import com.revature.pixelraffle.ui.navigation.BottomNavigationBar
import com.revature.pixelraffle.ui.navigation.NavScreens
import com.revature.pixelraffle.ui.theme.*
import com.revature.pixelraffle.viewmodel.UserViewModel


//@Preview
//@Composable
//fun previewMainMenu(){
//    PixelRaffleTheme() {
//        MainMenuScreen()
//    }
//
//}


@Composable
fun MainMenuScreen(navController: NavController,userViewModel: UserViewModel){
    var RoomID = rememberSaveable{ mutableStateOf("") }
    val RoomNum = "123"
    var context = LocalContext.current
    //val user= userViewModel.getAllUserLis().observeAsState(arrayListOf())
    //val listHolder = user.value

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {BottomNavigationBar(navController)}) {

        Surface(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(y = 80.dp),horizontalArrangement = Arrangement.Center) {

                    Text(text = "WELCOME: ${userViewModel.currentUser.first_name} ${userViewModel.currentUser.last_name}!",textAlign = TextAlign.Center,fontFamily = FontFamily.Default,fontSize = 25.sp,fontWeight = FontWeight.Bold)


            }


            Column(modifier=Modifier.fillMaxWidth().offset(y=200.dp)) {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 10.dp),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceEvenly) {
                    OutlinedTextField(value = RoomID.value, onValueChange = {RoomID.value=it},
                        label = {Text(text = buildAnnotatedString {
                            append("Room ID")
                            addStyle(style = SpanStyle(color = Color.Red,), start = 0, end = 6)
                            addStyle(style = SpanStyle(color = Color.Black,), start = 6, end = 13) },
                            fontSize = 13.sp,)},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.9f))
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 10.dp),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceEvenly) {

                    Button(onClick = {
                        if (RoomID.value.equals(RoomNum)){
                            navController.navigate(NavScreens.Room.route)
                        }else{ Toast.makeText(context,"Room does not exist...", Toast.LENGTH_LONG).show() }
                        },
                        modifier= Modifier
                        .padding(4.dp)
                        .width(350.dp),shape = CircleShape,
                    ) {
                        Text(text = "Join Room", modifier = Modifier.padding(2.dp),color=Color.White)
                    }

                }


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 30.dp),verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceEvenly) {

                    Button(
                        onClick = { navController.navigate(NavScreens.CreateRoom.route) },
                        modifier = Modifier
                            .padding(4.dp)
                            .width(350.dp),
                        shape = CircleShape,
                    ) {
                        Text(text = "Create Room", modifier = Modifier.padding(2.dp), color = Color.White)
                    }
                }

            }





            Row(modifier = Modifier
                .fillMaxWidth()
                .offset(y = 70.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.fillMaxWidth(),horizontalAlignment = Alignment.CenterHorizontally) {
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
                    modifier = Modifier.size(70.dp,70.dp).border(2.dp,color = MaterialTheme.colors.primary,shape = CircleShape)) {

                    Image(painter = painterResource(id = R.drawable.facebook), contentDescription ="facebook",Modifier.scale(.6f) )


                }

                //Twitter icon
                FloatingActionButton(onClick = { /*TODO*/ },
                    shape = CircleShape,
                    backgroundColor = Color.White,
                    modifier = Modifier.size(70.dp,70.dp).border(2.dp,color = MaterialTheme.colors.primary,shape = CircleShape)) {

                    Image(painter = painterResource(id = R.drawable.twitter), contentDescription ="twitter",Modifier.scale(.6f) )


                }

                //
                FloatingActionButton(onClick = { /*TODO*/ },
                    shape = CircleShape,
                    backgroundColor = Color.White,
                    modifier = Modifier.size(70.dp,70.dp).border(2.dp,color = MaterialTheme.colors.primary,shape = CircleShape)) {

                    Image(painter = painterResource(id = R.drawable.instagram), contentDescription ="instagram",Modifier.scale(.6f) )

                }


            }




        }

    }

}