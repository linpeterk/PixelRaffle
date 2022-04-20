package com.revature.pixelraffle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.listviewcustomizedv1.ui.theme.raffles
import com.revature.p3test.ui.DrawingApp
import com.revature.pixelraffle.ui.navigation.BottomNavigationBar
//import androidx.navigation.NavController
import com.revature.pixelraffle.ui.navigation.NavScreens
import com.revature.pixelraffle.ui.navigation.Navigation
import com.revature.pixelraffle.ui.screens.adama.RaffleHistories
import com.revature.pixelraffle.ui.screens.adama.UserProfileImage
import com.revature.pixelraffle.ui.screens.carlos.PhotoPickerIcon
import com.revature.pixelraffle.ui.screens.carlos.rememberSketchbookController


import com.revature.pixelraffle.ui.theme.PixelRaffleTheme
import com.revature.pixelraffle.viewmodel.TheViewModel
import com.revature.pixelraffle.viewmodel.UserViewModel



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
                   //RaffleHistories(raffles)
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


    val sketchbookController = rememberSketchbookController()

    LaunchedEffect(Unit) {
        sketchbookController.setPaintStrokeWidth(23f)
        sketchbookController.setPaintColor(Color.Red)
    }

    Scaffold(

        topBar = {
            TopAppBar(
                elevation = 2.dp,
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                title = {
                    Text("Pixel Raffle")
                    PhotoPickerIcon(controller = sketchbookController)
                    Button(onClick = {
                        navController.navigate(NavScreens.Room.route)

                    }){
                        Text("Temp navigate to Room")

                    }

                    UserProfileImage()

                },

                actions = {}
            )
        }
    ) { paddingValues: PaddingValues ->
        DrawingApp(paddingValues)
    }
}

