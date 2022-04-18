package com.revature.pixelraffle.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.revature.pixelraffle.createRoom
import com.revature.pixelraffle.ui.screens.adama.LoginPage
import com.revature.pixelraffle.ui.screens.adama.RegisterPage
import com.revature.pixelraffle.ui.screens.adama.UserActivityHistories
import com.revature.pixelraffle.ui.screens.lyle.MainMenuScreen
import com.revature.pixelraffle.ui.screens.lyle.MainOverallScreen
import com.revature.pixelraffle.ui.screens.lyle.RollRoom
import com.revature.pixelraffle.ui.screens.peter.ProfileScreen
import com.revature.pixelraffle.ui.screens.peter.RoomScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavScreens.MainOverall.route) {

        //adama
        composable(NavScreens.Register.route) {
             RegisterPage(navController)
        }

        composable(NavScreens.Login.route) {
            LoginPage(navController)
        }

        composable(NavScreens.UserActivityHistories.route) {
            UserActivityHistories(navController)
        }

        //lyles
        composable(NavScreens.MainOverall.route) {
            MainOverallScreen(navController)
        }
        composable(NavScreens.MainMenu.route) {
            MainMenuScreen(navController)
        }
        composable(NavScreens.RollRoom.route) {
            RollRoom(navController)
        }

        //carlos

        composable(NavScreens.CreateRoom.route) {
            createRoom(navController)
        }
        composable(NavScreens.Ticket.route) {
            //ticket
        }

        //peter

        composable(NavScreens.Profile.route) {
            ProfileScreen(navController)
        }
        composable(NavScreens.Room.route) {
            RoomScreen(navController)
        }
        composable(NavScreens.NearBy.route) {
            //NearBy
        }

    }
}