package com.example.pixelraffle.ui.screens.adama

import android.provider.ContactsContract
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pixelraffle.viewmodel.UserViewModel
import com.example.pixelraffle.R
import com.example.pixelraffle.ui.theme.graySurface


@Preview
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

    //User Password
    val passwordVisibilty = rememberSaveable{ mutableStateOf(false) }
    val confirmationOfPasswordVisibilty = rememberSaveable{ mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
            .padding(bottom = 150.dp)
    ) {
        Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 2.dp, top = 0.dp, end = 2.dp, bottom = 0.dp)
            ){
                Image( 
                    painterResource(id = R.drawable.pixel_logoa_02),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                        .background(colorResource(id = R.color.white))
                        .clickable(
                            enabled = true,
                            onClickLabel = "Clickable Logo",
                            onClick = {
                                Toast
                                    .makeText(context, "Welcome to Pixel Raffle", Toast.LENGTH_LONG)
                                    .show()
                            }
                        )
                )
        }
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, top = 40.dp, end = 2.dp, bottom = 10.dp)
        ){
            Text(
                text = buildAnnotatedString {
                    append("REGISTER")
                    addStyle(
                        style = SpanStyle(
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,
                        ),
                        start = 0,
                        end = 4
                    )
                    addStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        ),
                       start = 4,
                        end = 8
                    )
                },
                fontSize = 24.sp,
            )
        }
        Spacer(modifier = Modifier.padding(2.dp))
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, top = 0.dp, end = 2.dp, bottom = 0.dp)
        )
        {
            //User Fisrt Name
            OutlinedTextField(
                value = firstName.value, onValueChange = { firstName.value = it },
                label = {
                    Text(
                        text = buildAnnotatedString {
                            append("First Name")
                            addStyle(
                                style = SpanStyle(
                                    color = Color.Red,
                                ),
                                start = 0,
                                end = 5
                            )
                            addStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                ),
                                start = 6,
                                end = 8
                            )
                        },
                        fontSize = 13.sp,
                    )
                },

                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, top = 0.dp, end = 2.dp, bottom = 0.dp)
        ){
            //User Last Name
            OutlinedTextField(value = lastName.value, onValueChange = {lastName.value=it},
                label = {Text(
                    text = buildAnnotatedString {
                        append("Last Name")
                        addStyle(
                            style = SpanStyle(
                                color = Color.Red,
                            ),
                            start = 0,
                            end = 4
                        )
                        addStyle(
                            style = SpanStyle(
                                color = Color.Black,
                            ),
                            start = 6,
                            end = 8
                        )
                    },
                    fontSize = 13.sp,

                )},

                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.9f))

        }

        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, top = 0.dp, end = 2.dp, bottom = 0.dp)
        ){
            //Email Addres textfield field
            OutlinedTextField(value = email.value, onValueChange = {email.value=it},
                label = {Text(
                    text = buildAnnotatedString {
                        append("Email Address")
                        addStyle(
                            style = SpanStyle(
                                color = Color.Red,
                            ),
                            start = 0,
                            end = 6
                        )
                        addStyle(
                            style = SpanStyle(
                                color = Color.Black,
                            ),
                            start = 6,
                            end = 13
                        )
                    },
                    fontSize = 13.sp,
                )},

                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.9f)
             )
        }
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, top = 0.dp, end = 2.dp, bottom = 0.dp)
        ){
            //Password Addres textfield field
            OutlinedTextField(value = Password.value, onValueChange = {Password.value=it},
                label = {Text(
                    text = buildAnnotatedString {
                        append("Create Password")
                        addStyle(
                            style = SpanStyle(
                                color = Color.Red,
                            ),
                            start = 0,
                            end = 6
                        )
                        addStyle(
                            style = SpanStyle(
                                color = Color.Black,
                            ),
                            start = 6,
                            end = 14
                        )
                    },
                    fontSize = 13.sp,

                )},

                singleLine = true,
                modifier = Modifier.fillMaxWidth(0.9f),
                trailingIcon = {
                    IconButton(
                        modifier=Modifier,
                        onClick = { /*TODO*/
                            passwordVisibilty.value= !passwordVisibilty.value
                        }) {

                        Image(painterResource(id = R.drawable.password_eye), contentDescription = null)
                        if(passwordVisibilty.value) Color.Red else Color.Gray
                    }
                },
                visualTransformation = if(passwordVisibilty.value) VisualTransformation.None
                else PasswordVisualTransformation()
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, top = 0.dp, end = 2.dp, bottom = 10.dp)
        ) {
            Button( modifier= Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, top = 0.dp, end = 40.dp, bottom = 0.dp)
                .shadow(
                    elevation = 15.dp,
                    shape = CircleShape,
                    clip = true
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black),

                onClick = {
                    if(firstName.value.isNullOrEmpty()||
                        lastName.value.isNullOrEmpty()||
                        email.value.isNullOrEmpty()||
                        Password.value.isNullOrEmpty()
                    ) {
                        Toast.makeText(context, "All field might be filed", Toast.LENGTH_LONG).show()
                    }else if(email.value.length <10){
                        Toast.makeText(context, "User Email should be at least 10 characters", Toast.LENGTH_LONG).show()
                    }else if(Password.value.length < 5){
                        Toast.makeText(context, "Password should be at least 5 characters", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(context, "Welcome On Board!", Toast.LENGTH_LONG).show()
                    }

                }) {
                    Text(
                        text = buildAnnotatedString {
                            append("CREATE ACCOUNT")
                            addStyle(
                                style = SpanStyle(
                                    color = Color.Red,
                                    fontWeight = FontWeight.Bold,
                                ),
                                start = 0,
                                end = 6
                            )
                            addStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold
                                ),
                                start = 6,
                                end = 13
                            )
                        },
                        fontSize = 15.sp,
                    )
            }
        }
        Spacer(modifier=Modifier.padding(0.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(2.dp)
        ){
            Text(
                text = buildAnnotatedString {
                    append("Already have an account?")
                    addStyle(
                        style = SpanStyle(
                            color = Color.Red,
                        ),
                        start = 0,
                        end = 12
                    )
                    addStyle(
                        style = SpanStyle(
                            color = Color.Black,
                        ),
                        start = 12,
                        end = 23
                    )
                },
                fontSize = 13.sp,
               modifier=Modifier.padding(start = 100.dp, top = 10.dp)
                )
            Button( modifier= Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, top = 0.dp, end = 40.dp, bottom = 0.dp)
                .shadow(
                    elevation = 15.dp,
                    shape = CircleShape,
                    clip = true
                ),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black),

                onClick = {

                }) {
                Text(
                    text = buildAnnotatedString {
                        append("Login")
                        addStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,
                            ),
                            start = 0,
                            end = 2
                        )
                        addStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            ),
                            start = 2,
                            end = 4
                        )
                    },
                    fontSize = 10.sp,
                   // modifier=Modifier.padding(start = 90.dp, top = 10.dp)
                )
            }
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
