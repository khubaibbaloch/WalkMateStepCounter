package com.WalkMateApp.walkmate

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.SoundScapeApp.soundscape.ui.theme.WalkMateThemes
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModel
import com.WalkMateApp.walkmate.WalkMateApp.MainViewModel.WalkMateViewModelFactory
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.RootNavGraph
import com.WalkMateApp.walkmate.ui.theme.WalkMateTheme


class MainActivity : ComponentActivity() {
    private val viewModel: WalkMateViewModel by viewModels {
        WalkMateViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentTheme = viewModel.currentTheme.collectAsState()
            WalkMateThemes(currentTheme.value.toInt(),this@MainActivity) {
                val navController = rememberNavController()
                RootNavGraph(navController = navController, viewModel = viewModel)
                // Call the permission check and request inside the LaunchedEffect
                checkPermissionAndRequest(navController,this)

            }
        }
    }

    @Composable
    private fun checkPermissionAndRequest(navController: NavController, activity: ComponentActivity) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        if (currentRoute == "home") {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.ACTIVITY_RECOGNITION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.ACTIVITY_RECOGNITION),
                    REQUEST_CODE_ACTIVITY_RECOGNITION
                )
            } else {
                // Permission is already granted, do something if needed
            }
        }
    }


    companion object {
        private const val REQUEST_CODE_ACTIVITY_RECOGNITION = 1
    }

    override fun onResume() {
        super.onResume()
        viewModel.resetDataOnDayChange()
    }
}