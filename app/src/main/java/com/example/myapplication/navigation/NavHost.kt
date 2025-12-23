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
import com.example.myapplication.screens.AddAccountPage
import com.example.myapplication.screens.AddCardPage
import com.example.myapplication.screens.AddPaymentPage
import com.example.myapplication.screens.CreateAccountPage
import com.example.myapplication.screens.CreateAccountPasswordPage
import com.example.myapplication.screens.EditProfilePage
import com.example.myapplication.screens.ForgotPasswordPage

@Composable
fun AppNavHost(
    navController: NavHostController,
    api: GroupApiService,
    modifier: Modifier = Modifier,
    AuthRepository: AuthRepository)
{
    val activity = (LocalContext.current as Activity)

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    )
    {
        composable("login")
        {
            LoginScreen(navController, AuthRepository, activity)
        }

        composable("forgot_password")
        {
            ForgotPasswordPage(navController)
        }

        composable("create_account")
        {
            CreateAccountPage(navController)
        }

        composable("create_account_password")
        {
            CreateAccountPasswordPage(navController)
        }

        composable("add_payment")
        {
            AddPaymentPage(navController)
        }

        composable("profile")
        {
            Profile(navController)
        }

        composable("edit_profile")
        {
            EditProfilePage(navController)
        }

        composable("add_card")
        {
            AddCardPage(navController)
        }

        composable("add_account")
        {
            AddAccountPage(navController)
        }

        composable("home") { HomeScreen(navController = navController, api = api,true) }
        composable("add") { CreateGroup(navController = navController) }
        composable(route = "group"){Group("Event4")}
        composable("activity") { ActivityPage() }
        
    }
}

