package com.example.pixelraffle.ui.navigation

import com.example.pixelraffle.R

sealed class NavScreens(var route: String, var icon: Int, var title: String){
    object Register : NavScreens("Register", R.drawable.ic_launcher_background, "Register")
    object Login : NavScreens("Login", R.drawable.ic_launcher_background, "Login")
    object UserProfilePage : NavScreens("UserProfilePage", R.drawable.ic_launcher_background, "UserProfilePage")
    object UserActivityHistories : NavScreens("UserActivityHistories", R.drawable.ic_launcher_background, "UserActivityHistories")

    //UserActivityHistories
}
