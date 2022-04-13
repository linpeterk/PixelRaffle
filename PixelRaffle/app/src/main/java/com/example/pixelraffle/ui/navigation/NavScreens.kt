package com.example.pixelraffle.ui.navigation

import com.example.pixelraffle.R

sealed class NavScreens(var route: String, var icon: Int, var title: String){
    object Register : NavScreens("Register", R.drawable.ic_launcher_background, "Register")
    object Login : NavScreens("Login", R.drawable.ic_launcher_background, "Login")
    object userActivityHistories : NavScreens("userActivityHistories", R.drawable.ic_launcher_background, "userActivityHistories")
}
