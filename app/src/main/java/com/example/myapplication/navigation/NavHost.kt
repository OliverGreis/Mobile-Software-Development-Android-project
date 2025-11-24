package com.example.myapplication.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.screens.ActivityPage
import com.example.myapplication.screens.CreateGroup
import com.example.myapplication.Profile
import com.example.myapplication.screens.Group
import com.example.myapplication.Controller.GroupApiService
import com.example.myapplication.screens.HomeScreen
@Composable
fun AppNavHost(navController: NavHostController, api: GroupApiService, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { HomeScreen(navController = navController, api = api) }
        composable("add") { CreateGroup() }
        composable("profile") { Profile() }
        composable("group") { Group() }
        composable("activity") { ActivityPage() }
        
    }
}

