package com.revature.pixelraffle.ui.navigation

import com.revature.pixelraffle.R

sealed class NavScreens(var route: String, var icon: Int, var title: String){

    //adama
    object Register : NavScreens("Register", R.drawable.ic_launcher_background, "Register")
    object Login : NavScreens("Login", R.drawable.ic_launcher_background, "Login")
    //object UserProfilePage : NavScreens("UserProfilePage", R.drawable.ic_launcher_background, "UserProfilePage")
    object RaffleHistories : NavScreens("RaffleHistories", R.drawable.ic_launcher_background, "History")

    //lyles
    object MainMenu :  NavScreens("MainMenu", R.drawable.ic_launcher_background, "Home")
    object MainOverall :  NavScreens("MainOverall", R.drawable.ic_launcher_background, "MainOverall")
    object RollRoom :  NavScreens("RollRoom", R.drawable.ic_launcher_background, "RollRoom")


    //carlos
    object CreateRoom :  NavScreens("CreateRoom", R.drawable.ic_launcher_background, "Create Room")
    object Ticket :  NavScreens("Ticket", R.drawable.ic_launcher_background, "Ticket")

    //Peter
    object Profile :  NavScreens("Profile", R.drawable.ic_launcher_background, "Profile")
    object Room :  NavScreens("Room", R.drawable.ic_launcher_background, "Room")
    object NearBy :  NavScreens("NearBy", R.drawable.ic_launcher_background, "NearBy")


}
