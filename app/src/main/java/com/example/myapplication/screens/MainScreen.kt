package com.example.myapplication.screens
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.ui.theme.MyApplication7Theme
import com.example.myapplication.navigation.AppNavHost
import com.example.myapplication.api.userApi
import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.repository.AuthRepositoryImp
import com.example.myapplication.viewmodel.UserViewModel


class MainActivity : ComponentActivity() {
    private val repo: AuthRepository by lazy { AuthRepositoryImp(applicationContext) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        
        enableEdgeToEdge()
        setContent {
            MyApplication7Theme {
                    MainScreen(authRepository = repo)
                }
        }
    }
}

@Composable
fun MainScreen(authRepository: AuthRepository) {
    val navController = rememberNavController()
    val userapi = userApi
    val userViewModel: UserViewModel = viewModel()

    val backStackEntry = navController.currentBackStackEntryAsState().value
    val route = backStackEntry?.destination?.route

    val hideTopBarRoutes = setOf("login")
    val showTopBar = route !in hideTopBarRoutes

    val hideBotBarRoutes = setOf("login", "create_account", "create_account_password", "add_payment", "forgot_password")
    val showBotBar = route !in hideBotBarRoutes


    Scaffold(
        topBar = {
            if(showTopBar){
                TopAppBar(navController)
            }
        },
        bottomBar = {
            if(showBotBar){
                BotAppBarExample(navController)
            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            userApi = userapi,
            userViewModel = userViewModel,
            AuthRepository = authRepository,
        )
    }
}

