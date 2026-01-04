package com.example.myapplication.screens
import androidx.compose.runtime.Composable
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.navigation.NavHostController
import kotlin.jvm.java
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.Color


@Composable
fun BotAppBarExample(navController: NavHostController) {
    val context = LocalContext.current

    BottomAppBar(
        modifier = Modifier
            .height(85.dp),
            containerColor = Color(android.graphics.Color.parseColor("#88C25F")),
        actions = {
            IconButton(
                onClick = { navController.navigate("home") },
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            ) {
                Icon(
                    Icons.Filled.Home,
                    contentDescription = "Home",
                    modifier = Modifier.size(75.dp)
                )
            }

            IconButton(
                onClick = { navController.navigate("add") },
                modifier = Modifier.padding(horizontal = 80.dp)
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "Add",
                    modifier = Modifier.size(75.dp)
                )
            }

            IconButton(
//                onClick = {
//                    val intent = Intent(context, Profile::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                    context.startActivity(intent)
//                },
                onClick = { navController.navigate("setting") },
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            ) {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.size(75.dp)
                )
            }
        }
    )
}
