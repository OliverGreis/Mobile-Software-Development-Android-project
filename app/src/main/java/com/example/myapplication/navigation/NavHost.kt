package com.example.myapplication.navigation
import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.Controller.ApiClient
import com.example.myapplication.screens.ActivityPage
import com.example.myapplication.screens.CreateGroup
import com.example.myapplication.screens.Group
import com.example.myapplication.Controller.GroupApiService
import com.example.myapplication.Controller.NotificationsSettingApiService
import com.example.myapplication.Controller.UserApiService
import com.example.myapplication.repository.NotificationSettingRepository
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.LoginScreen
import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.repository.SettingPreference
import com.example.myapplication.screens.AddAccountPage
import com.example.myapplication.screens.AddCardPage
import com.example.myapplication.screens.AddPaymentPage
import com.example.myapplication.screens.CreateAccountPage
import com.example.myapplication.screens.CreateAccountPasswordPage
import com.example.myapplication.screens.EditProfilePage
import com.example.myapplication.screens.ForgotPasswordPage
import com.example.myapplication.screens.SettingScreen
import com.example.myapplication.viewmodel.SettingViewModel
import com.example.myapplication.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavHost(
    navController: NavHostController,
    api: GroupApiService,
    userApi: UserApiService,
    modifier: Modifier = Modifier,
    AuthRepository: AuthRepository,
    userViewModel: UserViewModel
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
            CreateAccountPage(navController, userViewModel)
        }

        composable("create_account_password")
        {
            CreateAccountPasswordPage(navController, userApi, userViewModel)
        }

        composable("add_payment")
        {
            AddPaymentPage(navController, userApi, userViewModel)
        }

//        composable("profile")
//        {
//            Profile(navController, userApi)
//        }

        composable("edit_profile")
        {
            EditProfilePage(navController, userApi, userViewModel)
        }

        composable("add_card")
        {
            AddCardPage(navController, userApi, userViewModel)
        }

        composable("add_account")
        {
            AddAccountPage(navController, userApi, userViewModel)
        }

        composable("home") { HomeScreen(navController = navController, api = api,true) }
        composable("add") { CreateGroup(navController = navController) }
        composable(route = "group"){Group("Event4")}
        composable("activity") { ActivityPage() }
        composable("setting") {

            val pref = remember { SettingPreference(activity) }
            val remote = NotificationSettingRepository(ApiClient.retrofit.create(
                NotificationsSettingApiService::class.java))
            val vm: SettingViewModel = viewModel(
                factory = SettingViewModel.Factory(pref, remote)
            )

            val state by vm.state.collectAsState()

            SettingScreen(
                modifier = modifier,
                state = state,
                onBack = { navController.popBackStack() },
                onToggleNotifications = vm::toggleNotifications,
                onToggleGroupNotifications = vm::toggleGroupNotifications
            )
        }
    }
}


