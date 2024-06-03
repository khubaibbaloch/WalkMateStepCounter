package com.WalkMateApp.walkmate.WalkMateApp.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.HomeScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.ProfileScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.GenderScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.HeightScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.SettingsScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.StatisticsScreen.StatisticsScreen


@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ScreenRoutes.HeightScreen.route) {
        composable(ScreenRoutes.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        composable(ScreenRoutes.SettingsScreen.route) {
            SettingsScreen(navController = navController)
        }

        composable(ScreenRoutes.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(ScreenRoutes.StatisticsScreen.route) {
            StatisticsScreen(navController = navController)
        }
        composable(ScreenRoutes.GenderScreen.route) {
            GenderScreen(navController = navController)
        }
        composable(ScreenRoutes.HeightScreen.route) {
            HeightScreen(navController = navController)
        }
    }
}