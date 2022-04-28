package com.revature.pixelraffle.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.revature.pixelraffle.R

sealed class NavScreens(var route: String, val icon: ImageVector, var title: String){

    //adama
    object Register : NavScreens("Register", Icons.Default.Home, "Register")
    object Login : NavScreens("Login", Icons.Default.Home, "Login")
    //object UserProfilePage : NavScreens("UserProfilePage", R.drawable.ic_launcher_background, "UserProfilePage")
    object RaffleHistories : NavScreens("RaffleHistories", Icons.Default.History, "History")

    //lyles
    object MainMenu :  NavScreens("MainMenu", Icons.Default.Home, "Home")
    object MainOverall :  NavScreens("MainOverall", Icons.Default.Home, "MainOverall")
    object RollRoom :  NavScreens("RollRoom", Icons.Default.Home, "RollRoom")


    //carlos
    object CreateRoom :  NavScreens("CreateRoom", Icons.Default.Create, "Create Room")
    object Ticket :  NavScreens("Ticket", Icons.Default.Home, "Ticket")

    //Peter
    object Profile :  NavScreens("Profile", Icons.Default.Person, "Profile")
    object Room :  NavScreens("Room", Icons.Default.Home, "Room")
    object NearBy :  NavScreens("NearBy", Icons.Default.Cloud, "NearBy")


}
