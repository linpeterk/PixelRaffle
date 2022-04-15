package com.example.pixelraffle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.pixelraffle.ui.screens.lyle.MainMenuScreen
import com.example.pixelraffle.ui.screens.lyle.MainOverallScreen

import androidx.lifecycle.ViewModelProvider
import com.example.p3test.ui.DrawingApp
//import androidx.navigation.NavController
import com.example.pixelraffle.ui.navigation.NavScreens
import com.example.pixelraffle.ui.screens.adama.RegisterPage
import com.example.pixelraffle.ui.screens.lyle.RollRoom
import com.example.pixelraffle.ui.screens.peter.DrawScreen

import com.example.pixelraffle.ui.screens.peter.ProfileScreen
import com.example.pixelraffle.ui.theme.PixelRaffleTheme
import com.example.pixelraffle.viewmodel.TheViewModel
import com.example.pixelraffle.viewmodel.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixelRaffleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }
            }
        }
    }
}

@Composable
fun createRoom() {
    Scaffold(

        topBar = {
            TopAppBar(
                elevation = 2.dp,
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                title = {
                    Text("Pixel Raffle")
                },

                actions = {}
            )
        }
    ) { paddingValues: PaddingValues ->
        DrawingApp(paddingValues)
    }
}

