package com.example.pixelraffle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.example.pixelraffle.ui.screens.lyle.MainMenuScreen
import com.example.pixelraffle.ui.screens.lyle.MainOverallScreen

import androidx.lifecycle.ViewModelProvider
//import androidx.navigation.NavController
import com.example.pixelraffle.ui.navigation.NavScreens
import com.example.pixelraffle.ui.screens.adama.RegisterPage
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
                  //DrawScreen()
                   // RegisterPage()

                    ProfileScreen()





                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name! for Pixel Raffle")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PixelRaffleTheme {
        Greeting("Android")
    }
}