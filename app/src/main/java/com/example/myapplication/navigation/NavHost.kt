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
import com.example.myapplication.api.ApiClient
import com.example.myapplication.api.AuthApiService
import com.example.myapplication.screens.ActivityPage
import com.example.myapplication.screens.CreateGroup
import com.example.myapplication.screens.Group
import com.example.myapplication.api.GroupApiService
import com.example.myapplication.api.NotificationsSettingApiService
import com.example.myapplication.api.UserApiService
import com.example.myapplication.api.DeviceTokenApi
import com.example.myapplication.repository.NotificationSettingRepository
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.repository.GroupRepository
import com.example.myapplication.repository.SettingPreference
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.screens.AddAccountPage
import com.example.myapplication.screens.AddCardPage
import com.example.myapplication.screens.AddPaymentPage
import com.example.myapplication.screens.CreateAccountPage
import com.example.myapplication.screens.CreateAccountPasswordPage
import com.example.myapplication.screens.EditProfilePage
import com.example.myapplication.screens.ForgotPasswordPage
import com.example.myapplication.screens.LoginPage2
import com.example.myapplication.screens.ProfilePage
import com.example.myapplication.screens.SettingScreen
import com.example.myapplication.viewmodel.AuthViewModel
import com.example.myapplication.viewmodel.GroupViewModel
import com.example.myapplication.viewmodel.HomeViewModel
import com.example.myapplication.viewmodel.SettingViewModel
import com.example.myapplication.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun AppNavHost(
    navController: NavHostController,
    userApi: UserApiService,
    modifier: Modifier = Modifier,
    userViewModel: UserViewModel,
)
{
    val activity = (LocalContext.current as Activity)
    val userApi = remember { ApiClient.retrofit.create(UserApiService::class.java) }
    val deviceTokenApi = remember { ApiClient.retrofit.create(DeviceTokenApi::class.java) }
    val userRepo = remember { UserRepository(userApi, deviceTokenApi) }
    val authApi = remember { ApiClient.retrofit.create(AuthApiService::class.java) }
    val authRepo = remember { AuthRepository(authApi) }
    val authFactory = remember { AuthViewModel.Factory(authRepo, userRepo) }
    val authVm: AuthViewModel = viewModel(factory = authFactory)

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    )
    {
        composable("login")
        {

            LoginPage2(navController = navController, authViewModel = authVm)
        }

        composable("create_account")
        {


            CreateAccountPage(navController, userViewModel, authVm)
        }

        composable("forgot_password")
        {
            ForgotPasswordPage(navController)
        }


        composable("create_account_password")
        {
            CreateAccountPasswordPage(navController, userApi, userViewModel)
        }

        composable("add_payment")
        {
            AddPaymentPage(navController, userApi, userViewModel, authViewModel = authVm)
        }

        composable("profile")
        {
            ProfilePage(navController, userApi)
        }

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

        composable("home") {
            val groupApi = remember { ApiClient.retrofit.create(GroupApiService::class.java) }

            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.Factory(groupApi)
            )

            HomeScreen(
                navController = navController,
                viewModel = homeViewModel,
                refreshTrigger = false,
                authViewModel = authVm
            )
        }

        composable("add") {
            val groupApi = remember { ApiClient.retrofit.create(GroupApiService::class.java) }
            val groupRepo = remember { GroupRepository(groupApi) }
            val groupViewModel: GroupViewModel = viewModel(
                factory = GroupViewModel.Factory(groupRepo)
            )
            CreateGroup(navController = navController, groupViewModel = groupViewModel)
        }
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


