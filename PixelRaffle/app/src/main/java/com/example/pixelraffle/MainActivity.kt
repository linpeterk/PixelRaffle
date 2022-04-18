package com.example.pixelraffle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.pixelraffle.ui.screens.lyle.MainMenuScreen
import com.example.pixelraffle.ui.screens.lyle.MainOverallScreen

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.p3test.ui.DrawingApp
import com.example.pixelraffle.ui.navigation.BottomNavigationBar
//import androidx.navigation.NavController
import com.example.pixelraffle.ui.navigation.NavScreens
import com.example.pixelraffle.ui.navigation.Navigation
import com.example.pixelraffle.ui.screens.adama.LoginPage
import com.example.pixelraffle.ui.screens.adama.RegisterPage

import com.example.pixelraffle.ui.screens.lyle.RollRoom
import com.example.pixelraffle.ui.screens.adama.UserProfileImage


import com.example.pixelraffle.ui.screens.peter.ProfileScreen
import com.example.pixelraffle.ui.theme.PixelRaffleTheme
import com.example.pixelraffle.viewmodel.TheViewModel
import com.example.pixelraffle.viewmodel.UserViewModel



class MainActivity : ComponentActivity() {

    private lateinit var theViewModel: TheViewModel
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        

        theViewModel = ViewModelProvider(this).get(TheViewModel::class.java) // General viewmodel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java) // Userviewmodel


        setContent {
            val navController = rememberNavController()
            PixelRaffleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                Navigation(navController = navController)

                }
            }
        }
    }
}

@Composable
fun Default(navController: NavController) {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    when (navBackStackEntry?.destination?.route) {
        "MainOverall" -> {
            // Show BottomBar and TopBar
            bottomBarState.value = false
            //      Log.d("Landing page entry", "Landed")
        }
        "Login" -> {
            // Show BottomBar and TopBar
            bottomBarState.value = false
        }
        "Signup" -> {
            // Show BottomBar and TopBar
            bottomBarState.value = false
        }
        "MainMenu" -> {
            // Hide BottomBar and TopBar
            bottomBarState.value = true
        }
    }

    Scaffold(

        modifier = Modifier,
        bottomBar = {if(bottomBarState.value) {
            BottomNavigationBar(navController)
        }else{

        }

        }
    ) {

        Box(modifier = Modifier.padding(bottom = 56.dp)) {

            Navigation(navController = navController)
        }

    }


}

@Composable
fun createRoom(navController: NavController) {
    Scaffold(

        topBar = {
            TopAppBar(
                elevation = 2.dp,
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                title = {
                    Text("Pixel Raffle")
                    Button(onClick = {
                        navController.navigate(NavScreens.Room.route)

                    }){
                        Text("Temp navigate to Room")

                    }
                },

                actions = {}
            )
        }
    ) { paddingValues: PaddingValues ->
        DrawingApp(paddingValues)
    }
}

