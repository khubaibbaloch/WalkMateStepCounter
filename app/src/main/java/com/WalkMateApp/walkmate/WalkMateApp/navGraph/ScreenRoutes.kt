package com.WalkMateApp.walkmate.WalkMateApp.navGraph

sealed class ScreenRoutes(val route:String) {
    data object HomeScreen:ScreenRoutes("HomeScreenRoute")
    data object SettingsScreen:ScreenRoutes("SettingsScreenRoute")

    data object ProfileScreen:ScreenRoutes("ProfileScreenRoute")
    data object StatisticsScreen:ScreenRoutes("StatisticsScreenRoute")
    data object GenderScreen:ScreenRoutes("GenderScreenRoute")
    data object HeightScreen:ScreenRoutes("HeightScreenRoute")
    data object WeightScreen:ScreenRoutes("WeightScreenRoute")
    data object SetGoalScreen:ScreenRoutes("SetGoalScreenRoute")
    data object UserNameScreen:ScreenRoutes("SetUserNameScreenRoute")
    data object WaterIntakeScreen:ScreenRoutes("WaterIntakeScreenRoute")
    data object InstructionScreen:ScreenRoutes("InstructionScreenRoute")
    data object AboutUsScreen:ScreenRoutes("AboutUsScreenRoute")
    data object HeartRateScreen:ScreenRoutes("HeartRateScreenRoute")

    data object UpdateGenderScreen:ScreenRoutes("UpdateGenderScreenRoute")
    data object UpdateHeightScreen:ScreenRoutes("UpdateHeightScreenRoute")
    data object UpdateWeightScreen:ScreenRoutes("UpdateWeightScreenRoute")
    data object UpdateUserNameScreen:ScreenRoutes("UpdateSetUserNameScreenRoute")
    data object AboutYouScreen:ScreenRoutes("AboutYouScreenRoute")

}