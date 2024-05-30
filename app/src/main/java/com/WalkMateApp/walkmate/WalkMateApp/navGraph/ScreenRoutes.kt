package com.WalkMateApp.walkmate.WalkMateApp.navGraph

sealed class ScreenRoutes(val route:String) {
    data object HomeScreen:ScreenRoutes("HomeScreenRoute")
    data object SettingsScreen:ScreenRoutes("SettingsScreenRoute")
}