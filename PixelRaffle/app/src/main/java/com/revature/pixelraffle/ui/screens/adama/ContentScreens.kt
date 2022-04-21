package com.revature.pixelraffle.ui.screens.adama

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.revature.pixelraffle.R
import com.revature.pixelraffle.database.datamodel.UserRow
import com.revature.pixelraffle.ui.components.LogoA
import com.revature.pixelraffle.ui.navigation.NavScreens
import com.revature.pixelraffle.viewmodel.UserViewModel


//@Preview
//New User Register Page//
//fun RegisterPage(navController: NavController, userViewModel: UserViewModel)

@Composable
fun RegisterPage(navController:NavController, userViewModel:UserViewModel){
    val context = LocalContext.current

    //User First Name variable
    val firstName = rememberSaveable{ mutableStateOf("")}

    //User First Last Name variable
    val lastName = rememberSaveable{ mutableStateOf("")}

    //User Email Address variable
    val email = rememberSaveable{ mutableStateOf("")}

    //User Password variable
    val password = rememberSaveable{ mutableStateOf("")}

    //User Address variable
    val Adrress = rememberSaveable{ mutableStateOf("")}

    //User User ZipCode variable
    val ZipCode = rememberSaveable{ mutableStateOf("")}

    //User Password Visibility
    val passwordVisibilty = rememberSaveable{ mutableStateOf(false) }
    val confirmationOfPasswordVisibilty = rememberSaveable{ mutableStateOf(false) }

    val scrollState = rememberScrollState()
    Surface(modifier = Modifier.fillMaxSize()) {
       // Image(painter = painterResource(id = com.example.pixelraffle.R.drawable.mnbase_02), contentDescription = "",alpha = .25f,contentScale = ContentScale.FillBounds)
        LogoA()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .padding(bottom = 0.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 2.dp, top = 150.dp, end = 2.dp, bottom = 5.dp)
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
                //User Password textfield field
                OutlinedTextField(value = password.value, onValueChange = {password.value=it},
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
                            onClick = {
                                //navController.navigate(NavScreens.Login.route)
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
                var validationStatus by remember{ mutableStateOf("")}
                Button( modifier= Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, top = 0.dp, end = 40.dp, bottom = 0.dp), shape = CircleShape,
                    //colors = ButtonDefaults.buttonColors(
                        //backgroundColor = Color.White,
                        //contentColor = Color.Black),

                    onClick = { validationStatus = UserValidations(firstName.value, lastName.value, email.value, password.value)
                        if(email.value.length <10){
                            Toast.makeText(context, "User Email should be at least 10 characters", Toast.LENGTH_LONG).show()
                        }else if(password.value.length < 5){
                            Toast.makeText(context, "Password should be at least 5 characters", Toast.LENGTH_LONG).show()
                        }else {
                            userViewModel.inserNewUser(
                                UserRow(
                                    first_name = firstName.value,
                                    last_name = lastName.value,
                                    email = email.value,
                                    password = password.value
                                )
                            )
                            Toast.makeText(context,"Sign Up Success", Toast.LENGTH_LONG).show()
                            navController.navigate(NavScreens.Login.route){
                                popUpTo(NavScreens.Login.route)
                            }
                        }

                    }) {
                    Text(
                        text = buildAnnotatedString {
                            append("CREATE ACCOUNT")
                            addStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                ),
                                start = 0,
                                end = 6
                            )
                            addStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                ),
                                start = 6,
                                end = 14
                            )
                        },
                        fontSize = 15.sp,
                    )
                }
            }
            Spacer(modifier=Modifier.padding(0.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ){
                Text(
                    text = buildAnnotatedString {
                        append("Already have an account?")
                        addStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                //color = MaterialTheme.colors.primaryVariant
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
                    .padding(start = 10.dp, top = 0.dp, end = 40.dp, bottom = 0.dp),shape = CircleShape,
                    onClick = {
                        navController.navigate(NavScreens.Login.route)
                    }) {
                    Text(
                        text = buildAnnotatedString {
                            append("Login")
                            addStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                ),
                                start = 0,
                                end = 2
                            )
                            addStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                ),
                                start = 2,
                                end = 4
                            )
                        },
                        fontSize = 12.sp,
                    )
                }
            }
        }

    }
    //LogoA()

}

//User Login Page

@Composable
fun LoginPage(navController:NavController, userViewModel: UserViewModel) {
    val context = LocalContext.current

    //User Email Address variable
    val email = rememberSaveable { mutableStateOf("") }

    //User Password variable
    val Password = rememberSaveable { mutableStateOf("") }

    //User Password Visibility
    val passwordVisibilty = rememberSaveable{ mutableStateOf(false) }
    val confirmationOfPasswordVisibilty = rememberSaveable{ mutableStateOf(false) }

    //Get user list
    val userList = userViewModel.getAllUsersData.observeAsState(arrayListOf())

    Surface(modifier = Modifier.fillMaxSize()) {
      //  Image(painter = painterResource(id = com.example.pixelraffle.R.drawable.mnbase_02), contentDescription = "",alpha = .25f,contentScale = ContentScale.FillBounds)
        LogoA()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .padding(bottom = 0.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 2.dp, top = 100.dp, end = 2.dp, bottom = 5.dp)
            ){
                Text(
                    text = buildAnnotatedString {
                        append("LOGIN PAGE")
                        addStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,
                            ),
                            start = 0,
                            end = 5
                        )
                        addStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            ),
                            start = 5,
                            end = 9
                        )
                    },
                    fontSize = 24.sp,
                )
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
            Spacer(modifier=Modifier.padding(0.dp))
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 2.dp, top = 0.dp, end = 2.dp, bottom = 0.dp)
            ){
                //User Password textfield field
                OutlinedTextField(value = Password.value, onValueChange = {Password.value=it},
                    label = {Text(
                        text = buildAnnotatedString {
                            append("Enter Password")
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
            Button( modifier= Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, top = 0.dp, end = 40.dp, bottom = 0.dp), shape = CircleShape,

                onClick = {
                    if(email.value.isNullOrEmpty()){
                        Toast.makeText(context, "Email cannot be blank", Toast.LENGTH_LONG).show()
                    }else if (Password.value.isNullOrEmpty()){
                        Toast.makeText(context, "Password cannot be blank", Toast.LENGTH_LONG).show()
                    }else{
                        val listHolder = userList.value
                        listHolder.forEach { userRow ->
                            if(email.value.equals(userRow.email) && Password.value.equals(userRow.password)){
                                Toast.makeText(context,"Welcome  ${userRow.first_name}", Toast.LENGTH_LONG).show()
                                navController.navigate(NavScreens.MainMenu.route){
                                    popUpTo(NavScreens.MainMenu.route)
                                }
                        }else {
                                Toast.makeText(context,"Please enter a valid Email and Password", Toast.LENGTH_LONG).show()
                        }
                        }

                    }

                }) {
                Text(
                    text = buildAnnotatedString {
                        append("LOGIN")
                        addStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                            ),
                            start = 0,
                            end = 3
                        )
                        addStyle(
                            style = SpanStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            start = 3,
                            end = 5
                        )
                    },
                    fontSize = 15.sp,
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Row(horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
            ){
                Text(
                    text = buildAnnotatedString {
                        append("Don't have an account?")
                        addStyle(
                            style = SpanStyle(
                                color = Color.Red,
                                //color = MaterialTheme.colors.primaryVariant
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
                    .padding(start = 10.dp, top = 0.dp, end = 40.dp, bottom = 0.dp), shape = CircleShape,

                    onClick = {
                        navController.navigate(NavScreens.Register.route)
                    }) {
                    Text(
                        text = buildAnnotatedString {
                            append("SignUp")
                            addStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                ),
                                start = 0,
                                end = 4
                            )
                            addStyle(
                                style = SpanStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                ),
                                start = 4,
                                end = 5
                            )
                        },
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
}

//User's Profile and Activities Page
@Preview
@Composable
fun UserProfilePage(){
    val context = LocalContext.current
    val notification = rememberSaveable{mutableStateOf("")}
    if(notification.value.isNotEmpty()){
        Toast.makeText(context, notification.value, Toast.LENGTH_LONG).show()
        notification.value = ""
    }
    var firstName by rememberSaveable{ mutableStateOf("Room Database")}
    var lastName by rememberSaveable{ mutableStateOf("Room Database")}
    var Email by rememberSaveable{ mutableStateOf("Room Database")}
    var Password by rememberSaveable{ mutableStateOf("Room Database")}

    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(8.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text="Cancel",
                modifier = Modifier.clickable { notification.value = "Cancelled" })
            Text(text = "Save",
                modifier = Modifier.clickable { notification.value = "Profile updated" })
        }
        UserProfileImage()
        Spacer(modifier=Modifier.padding(5.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text="First Name: ", modifier = Modifier.width(100.dp))
            TextField(value = firstName, onValueChange = {firstName = it}, colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                textColor = Color.Black
            ))
        }
        Spacer(modifier=Modifier.padding(5.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text="Last Name: ", modifier = Modifier.width(100.dp))
            TextField(value = lastName, onValueChange = {lastName = it}, colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                textColor = Color.Black
            ))
        }
    }
}
//val globalTest = """"
//User Profile Image
@Preview
@Composable
fun UserProfileImage() {
    val imageUri = rememberSaveable{ mutableStateOf("")}
    val painter = rememberAsyncImagePainter(
        if(imageUri.value.isEmpty())
            R.drawable.default_user_image
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()
    ) {
            uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
        Log.d("ImageURI", "${imageUri.value}")
    }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ){
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    //.wrapContentSize()
                    .size(100.dp)
                    .clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )
        }
       // Text(text="Change Profile Picture")
    }
}

@Preview
@Composable
fun GetProfileImage(){

    var firstName by rememberSaveable{ mutableStateOf("F_Name")}
    var lastName by rememberSaveable{ mutableStateOf("L_Name")}

    val imageUri = rememberSaveable{ mutableStateOf("")}
    val painter = rememberAsyncImagePainter(
        if(imageUri.value.isEmpty())
            R.drawable.default_user_image
        else
            imageUri.value
    )
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()
    ) {
            uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(shape = CircleShape,
            modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
        ){
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    //.wrapContentSize()
                    .size(100.dp),
                    //.clickable { launcher.launch("image/*") },
                contentScale = ContentScale.Crop
            )
        }
        Text(text="$firstName  $lastName")
        Spacer(modifier = Modifier.padding(5.dp))
        Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 2.dp, top = 20.dp, end = 2.dp, bottom = 5.dp)
        ){
            Text(
                text = buildAnnotatedString {
                    append("YOUR ACTIVITY HISTORIES")
                },
                fontSize = 24.sp,fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(2.dp))

    }
}
//User Activities History

@Composable
fun UserActivityHistories(navController:NavController){
    Surface(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = com.revature.pixelraffle.R.drawable.mnbase_02), contentDescription = "",alpha = .25f,contentScale = ContentScale.FillBounds)

        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
        ) {
            GetProfileImage()
        }
    }
}





