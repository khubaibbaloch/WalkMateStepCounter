package com.WalkMateApp.walkmate.WalkMateApp.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.HomeScreen


@Composable
fun RootNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = ""){
        composable(ScreenRoutes.HomeScreen.route){
            HomeScreen(navController = navController)
        }
    }
}