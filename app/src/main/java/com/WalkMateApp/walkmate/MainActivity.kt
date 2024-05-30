package com.WalkMateApp.walkmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.WalkMateApp.walkmate.WalkMateApp.navGraph.RootNavGraph
import com.WalkMateApp.walkmate.ui.theme.WalkMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WalkMateTheme {
                val navController = rememberNavController()
                RootNavGraph(navController = navController)
            }
        }
    }
}