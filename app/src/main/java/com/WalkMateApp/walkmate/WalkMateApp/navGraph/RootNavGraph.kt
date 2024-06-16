package com.WalkMateApp.walkmate.WalkMateApp.navGraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.SoundScapeApp.soundscape.SoundScapeApp.ui.SettingsScreen.AboutUs.AboutUsScreen
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.ui.AboutYou.AboutYouScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.HeartRateScreen.HeartRateScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.HomeScreen.HomeScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.InstructionScreen.InstructionScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreen.ProfileScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.GenderScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.HeightScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.SetGoalScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.UserNameScreens
import com.WalkMateApp.walkmate.WalkMateApp.ui.ProfileScreens.WeightScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.ReminderScreen.ReminderScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.SettingsScreen.SettingsScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.StatisticsScreen.StatisticsScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.UpdateProfile.UpdateGenderScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.UpdateProfile.UpdateHeightScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.UpdateProfile.UpdateUserNameScreens
import com.WalkMateApp.walkmate.WalkMateApp.ui.UpdateProfile.UpdateWeightScreen
import com.WalkMateApp.walkmate.WalkMateApp.ui.WaterIntakeScreen.WaterIntakeScreen


@Composable
fun RootNavGraph(navController: NavHostController, viewModel: WalkMateViewModel) {
    val isUserAccountCreated = viewModel.isUserAccountCreated.collectAsState()
    NavHost(
        navController = navController,
        startDestination = if (isUserAccountCreated.value) ScreenRoutes.HomeScreen.route else ScreenRoutes.UserNameScreen.route
    ) {
        composable(ScreenRoutes.HomeScreen.route) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        composable(ScreenRoutes.SettingsScreen.route) {
            SettingsScreen(navController = navController, viewModel = viewModel)
        }
        composable(ScreenRoutes.ProfileScreen.route) {
            ProfileScreen(navController = navController, viewModel = viewModel)
        }
        composable(ScreenRoutes.StatisticsScreen.route) {
            StatisticsScreen(navController = navController, viewModel = viewModel)
        }
        composable(ScreenRoutes.GenderScreen.route) {
            GenderScreen(navController = navController, viewModel = viewModel)
        }
        composable(ScreenRoutes.HeightScreen.route) {
            HeightScreen(navController = navController, viewModel = viewModel)
        }
        composable(ScreenRoutes.WeightScreen.route) {
            WeightScreen(navController = navController, viewModel = viewModel)
        }
        composable(ScreenRoutes.SetGoalScreen.route) {
            SetGoalScreen(navController = navController, viewModel = viewModel)
        }
        composable(ScreenRoutes.UserNameScreen.route) {
            UserNameScreens(navController = navController, viewModel = viewModel)
        }
        composable(ScreenRoutes.WaterIntakeScreen.route) {
            WaterIntakeScreen(navController = navController, viewModel = viewModel)
        }
        composable(ScreenRoutes.InstructionScreen.route) {
            InstructionScreen(navController = navController)
        }
        composable(ScreenRoutes.AboutUsScreen.route) {
            AboutUsScreen(navController = navController)
        }
        composable(ScreenRoutes.HeartRateScreen.route) {
            HeartRateScreen(navController = navController,viewModel=viewModel)
        }
        composable(ScreenRoutes.UpdateGenderScreen.route) {
            UpdateGenderScreen(navController = navController,viewModel=viewModel)
        }
        composable(ScreenRoutes.UpdateHeightScreen.route) {
            UpdateHeightScreen(navController = navController,viewModel=viewModel)
        }
        composable(ScreenRoutes.UpdateWeightScreen.route) {
            UpdateWeightScreen(navController = navController,viewModel=viewModel)
        }
        composable(ScreenRoutes.UpdateUserNameScreen.route) {
            UpdateUserNameScreens(navController = navController,viewModel=viewModel)
        }
        composable(ScreenRoutes.AboutYouScreen.route) {
            AboutYouScreen(navController = navController,viewModel=viewModel)
        }
        composable(ScreenRoutes.ReminderScreen.route) {
            ReminderScreen(navController = navController,viewModel=viewModel)
        }
    }
}