package com.example.myapplication.screens

import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import android.R.color.white
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SmallTopAppBarExample(navController: NavHostController) {

            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(white),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
    )
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun SmallTopAppBarExampleXML(onBack: () -> Unit) {

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(white),
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("")
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}