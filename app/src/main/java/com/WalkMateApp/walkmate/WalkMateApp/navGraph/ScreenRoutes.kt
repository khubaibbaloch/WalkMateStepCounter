package com.WalkMateApp.walkmate.WalkMateApp.navGraph

sealed class ScreenRoutes(val route:String) {
    data object HomeScreen:ScreenRoutes("HomeScreenRoute")
}