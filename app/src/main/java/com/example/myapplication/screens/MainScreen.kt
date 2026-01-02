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
import com.example.myapplication.ui.theme.MyApplication7Theme
import com.example.myapplication.navigation.AppNavHost
import com.example.myapplication.Controller.groupApi
import com.example.myapplication.Controller.userApi
import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.repository.AuthRepositoryImp
import com.example.myapplication.viewmodel.UserRegirtationViewModel

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
    val api = groupApi
    val userapi = userApi
    val userViewModel: UserViewModel = viewModel()

    Scaffold(
        topBar = { SmallTopAppBarExample(navController) },
        bottomBar = { BotAppBarExample(navController) }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            api = api,
            userApi = userapi,
            userViewModel = userViewModel,
            AuthRepository = authRepository,
        )
    }
}

