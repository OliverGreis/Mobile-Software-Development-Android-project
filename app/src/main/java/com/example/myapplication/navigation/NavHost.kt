package com.example.myapplication.navigation
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.screens.ActivityPage
import com.example.myapplication.screens.CreateGroup
import com.example.myapplication.Profile
import com.example.myapplication.screens.Group
import com.example.myapplication.Controller.GroupApiService
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.LoginScreen
import com.example.myapplication.repository.AuthRepository

@Composable
fun AppNavHost(navController: NavHostController, api: GroupApiService, modifier: Modifier = Modifier, AuthRepository: AuthRepository) {
    val activity = (LocalContext.current as Activity)
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") { LoginScreen(navController, AuthRepository, activity) }
        composable("home") { HomeScreen(navController = navController, api = api,true) }
        composable("add") { CreateGroup(navController = navController) }
        composable("profile") { Profile() }
        composable(route = "group"){Group("Event4")}
        composable("activity") { ActivityPage() }
        
    }
}

