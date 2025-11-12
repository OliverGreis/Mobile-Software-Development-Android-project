package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.error
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.repository.AuthRepositoryImp
import com.example.myapplication.ui.theme.MyApplication7Theme
import com.example.myapplication.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Create real repo + VM (no DI)
        val repo = AuthRepositoryImp(applicationContext)
        val viewModel = ViewModelProvider(this, AuthVmFactory(repo))
            .get(AuthViewModel::class.java)

        setContent {
            MyApplication7Theme {
                // Pass the activity and viewmodel into your screen
                AuthScreen(activity = this, viewModel = viewModel)
            }
        }
    }

    class AuthVmFactory(private val repo: AuthRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AuthViewModel(repo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


    @Composable
    fun AuthScreen(activity: Activity, viewModel: AuthViewModel) {
        val state by viewModel.uiState.collectAsState()

        Scaffold { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(24.dp)
                    .fillMaxSize()
            ) {
                Text(
                    text = if (state.isLoggedIn) "Logged in" else "Logged out",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(12.dp))

                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    if (state.isLoggedIn) {
                        Text(text = "Hello, ${state.user?.name ?: "user"}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.logout(activity) }) {
                            Text("Log out")
                        }
                    } else {
                        Button(onClick = { viewModel.login(activity) }) {
                            Text("Log in with Auth0")
                        }
                    }
                }

                state.error?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}
