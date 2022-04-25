package com.revature.pixelraffle.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.listviewcustomizedv1.ui.theme.raffles
import com.revature.pixelraffle.createRoom
import com.revature.pixelraffle.ui.screens.adama.LoginPage
import com.revature.pixelraffle.ui.screens.adama.RaffleHistories
import com.revature.pixelraffle.ui.screens.adama.RegisterPage
import com.revature.pixelraffle.ui.screens.adama.UserActivityHistories
import com.revature.pixelraffle.ui.screens.lyle.MainMenuScreen
import com.revature.pixelraffle.ui.screens.lyle.MainOverallScreen
import com.revature.pixelraffle.ui.screens.lyle.RollRoom
import com.revature.pixelraffle.ui.screens.peter.ProfileScreen
import com.revature.pixelraffle.ui.screens.peter.RoomScreen
import com.revature.pixelraffle.viewmodel.UserViewModel

@Composable
fun Navigation(navController: NavHostController, userViewModel: UserViewModel) {
    NavHost(navController, startDestination = NavScreens.MainOverall.route) {

        //adama
        composable(NavScreens.Register.route) {
             RegisterPage(navController,userViewModel)
        }

        composable(NavScreens.Login.route) {
            LoginPage(navController, userViewModel)
        }

        composable(NavScreens.RaffleHistories.route) {
            //UserActivityHistories(navController)
            RaffleHistories(raffles,navController, userViewModel)

        }

        //lyles
        composable(NavScreens.MainOverall.route) {
            MainOverallScreen(navController)
        }
        composable(NavScreens.MainMenu.route) {
            MainMenuScreen(navController,userViewModel)
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