package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.repository.AuthRepositoryImp
import com.example.myapplication.ui.theme.MyApplication7Theme
import com.example.myapplication.viewmodel.AuthViewModel
import com.google.firebase.messaging.FirebaseMessaging

class TempLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Create real repo + VM (no DI)
        val repo = AuthRepositoryImp(applicationContext)
        val viewModel = ViewModelProvider(this, AuthVmFactory(repo))
            .get(AuthViewModel::class.java)
        // Test if token can be fetched
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                    return@addOnCompleteListener
                }
                val token = task.result
                Log.d("FCM", "Token: $token")
            }


        setContent {
            MyApplication7Theme {
                // Pass the activity and viewmodel into your screen
                NotificationPermissionRequester()
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
    fun NotificationPermissionRequester() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val context = LocalContext.current
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { }
            )

            LaunchedEffect(Unit) {
                val granted = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PermissionChecker.PERMISSION_GRANTED

                if (!granted) {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
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