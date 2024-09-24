package com.WalkMateApp.walkmate

import android.Manifest
import android.app.Activity
import android.app.AlarmManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModelFactory
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.RootNavGraph
import com.WalkMateApp.walkmate.WalkMateApp.ui.AboutYou.AboutYouScreen
import com.WalkMateApp.walkmate.ui.theme.WalkMateTheme
import com.powervpn.PowerVPNApp.PowerVPN.inAppUpdate.CheckForUpdates


class MainActivity : ComponentActivity() {
    private val viewModel: WalkMateViewModel by viewModels {
        WalkMateViewModelFactory(this)
    }


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val currentTheme = viewModel.currentTheme.collectAsState()
            WalkMateThemes(currentTheme.value.toInt(),this@MainActivity) {

                val navController = rememberNavController()

                //InApp Update check
                CheckForUpdates()

                RootNavGraph(navController = navController, viewModel = viewModel)
                // Call the permission check and request inside the LaunchedEffect
                checkPermissionAndRequest(navController,this)

            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @Composable
    private fun checkPermissionAndRequest(navController: NavController, activity: ComponentActivity) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        if (currentRoute == "home") {
            val missingPermissions = mutableListOf<String>()

            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACTIVITY_RECOGNITION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                missingPermissions.add(Manifest.permission.ACTIVITY_RECOGNITION)
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    missingPermissions.add(Manifest.permission.POST_NOTIFICATIONS)
                }
            }

            if (missingPermissions.isNotEmpty()) {
                ActivityCompat.requestPermissions(
                    activity,
                    missingPermissions.toTypedArray(),
                    REQUEST_CODE_ACTIVITY_RECOGNITION
                )
            } else {
                // Permissions are already granted, do something if needed
            }
        }
    }


    companion object {
        private const val REQUEST_CODE_ACTIVITY_RECOGNITION = 1
    }
}