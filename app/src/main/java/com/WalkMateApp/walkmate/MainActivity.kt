package com.WalkMateApp.walkmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.compose.rememberNavController
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
            WalkMateTheme {
                val navController = rememberNavController()
                RootNavGraph(navController = navController, viewModel = viewModel)
            }
        }
    }
}