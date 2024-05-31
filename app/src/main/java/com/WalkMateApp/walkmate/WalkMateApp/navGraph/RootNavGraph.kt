package com.WalkMateApp.walkmate.WalkMateApp.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.HomeScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.SettingsScreen


@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenRoutes.HomeScreen.route) {
        composable(ScreenRoutes.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(ScreenRoutes.SettingsScreen.route){
            SettingsScreen(navController = navController)
        }

        composable(ScreenRoutes.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }
    }
}