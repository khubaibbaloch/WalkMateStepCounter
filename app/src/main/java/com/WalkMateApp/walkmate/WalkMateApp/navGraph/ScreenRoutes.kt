package com.WalkMateApp.walkmate.WalkMateApp.navGraph

sealed class ScreenRoutes(val route:String) {
    data object HomeScreen:ScreenRoutes("HomeScreenRoute")
    data object SettingsScreen:ScreenRoutes("SettingsScreenRoute")

    data object ProfileScreen:ScreenRoutes("ProfileScreenRoute")
    data object StatisticsScreen:ScreenRoutes("StatisticsScreenRoute")
    data object GenderScreen:ScreenRoutes("GenderScreenRoute")
    data object HeightScreen:ScreenRoutes("HeightScreenRoute")
}