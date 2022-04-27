package com.revature.pixelraffle.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.revature.pixelraffle.ui.theme.graySurface

@Composable
fun BottomNavigationBar(navController: NavController) {

    val items = listOf(
        NavScreens.MainMenu,
        NavScreens.NearBy,
        NavScreens.RaffleHistories,
        NavScreens.Profile
    )

    BottomNavigation (
        backgroundColor = graySurface,
        contentColor = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(

                icon = { Icon(imageVector =item.icon, contentDescription = "Navigation Icon" )  },

                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White,
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        //Pop up to the start destination of the graph to
                        //avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(item.route) {
                                saveState = true

                            }
                        }
                        //avoid multiple copies of the same destination when
                        //reselecting the same item
                        //  launchSingleTop = true

                    }
                }
            )
        }
    }

}