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
import com.example.myapplication.Controller.UserApiService
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
    userApi: UserApiService,
    modifier: Modifier = Modifier,
    AuthRepository: AuthRepository,
    userViewModel: Any
)
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
            CreateAccountPage(navController, userApi, userViewModel)
        }

        composable("create_account_password")
        {
            CreateAccountPasswordPage(navController, userApi)
        }

        composable("add_payment")
        {
            AddPaymentPage(navController, userApi)
        }

        composable("profile")
        {
            Profile(navController, userApi)
        }

        composable("edit_profile")
        {
            EditProfilePage(navController, userApi, userViewModel)
        }

        composable("add_card")
        {
            AddCardPage(navController, userApi)
        }

        composable("add_account")
        {
            AddAccountPage(navController, userApi)
        }

        composable("home") { HomeScreen(navController = navController, api = api,true) }
        composable("add") { CreateGroup(navController = navController) }
        composable(route = "group"){Group("Event4")}
        composable("activity") { ActivityPage() }
        
    }
}

