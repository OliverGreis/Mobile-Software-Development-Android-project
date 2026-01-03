package com.example.myapplication.screens

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar (navController: NavHostController){
    val backStackEntry = navController.currentBackStackEntryAsState().value
    val route = backStackEntry?.destination?.route

    // Screens that the back button can go to
    val rootRoutes = setOf("home")

    val canGoBack = navController.previousBackStackEntry != null && route !in rootRoutes

    CenterAlignedTopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = { Text("") },
        navigationIcon = {
            if (canGoBack) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}