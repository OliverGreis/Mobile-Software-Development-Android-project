package com.example.myapplication.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.screens.ActivityPage
import com.example.myapplication.screens.CreateGroup
import com.example.myapplication.screens.Home
import com.example.myapplication.screens.Profile
import com.example.myapplication.screens.Group

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { Home(navController = navController) }
        composable("add") { CreateGroup() }
        composable("profile") { Profile() }
        composable("group") { Group() }
        composable("activity") { ActivityPage() }
        
    }
}