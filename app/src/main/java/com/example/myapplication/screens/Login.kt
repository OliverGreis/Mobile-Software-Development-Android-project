package com.example.myapplication.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.R.font.roboto_condensed_bold
import com.example.myapplication.repository.AuthRepository
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavHostController,
    authRepository: AuthRepository,
    activity: Activity
) {
    var isLoggingIn by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#88C25F")))
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Title
        Text(
            text = "Welcome",
            fontSize = 36.sp,
            fontFamily = FontFamily(Font(roboto_condensed_bold)),
            color = Color.White,
            modifier = Modifier.padding(bottom = 40.dp)
        )

        // Login Button
        Button(
            onClick = {
                isLoggingIn = true
                // Launch Auth0 login
                // Use a coroutine so this Composable stays clean
                coroutineScope.launch {
                    try {
                        val user = authRepository.login(activity)
                        // Navigate to Home after login
                        navController.navigate("home")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        isLoggingIn = false
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .width(220.dp)
                .height(55.dp)
        ) {
            Text(
                text = if (isLoggingIn) "Logging in..." else "Login with Auth0",
                fontSize = 18.sp,
                color = Color(android.graphics.Color.parseColor("#88C25F")),
                fontFamily = FontFamily(Font(roboto_condensed_bold))
            )
        }
    }
}
