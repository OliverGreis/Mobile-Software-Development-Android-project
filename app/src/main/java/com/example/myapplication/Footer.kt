package com.example.myapplication
import androidx.compose.runtime.Composable
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import com.example.myapplication.screens.CreateGroup
import androidx.navigation.NavHostController

@Composable
fun BotAppBarExample(navController: NavHostController) {

            BottomAppBar(
                modifier = Modifier.height(85.dp),
                actions = {
                    IconButton(onClick = { navController.navigate("home")  },modifier = Modifier.padding(horizontal = 20.dp)) {
                        Icon(Icons.Filled.Home, contentDescription = "Localized description", modifier = Modifier.size(75.dp))
                    }
                    IconButton(onClick = { navController.navigate("add") },modifier = Modifier.padding(horizontal = 80.dp)) {
                        Icon(Icons.Filled.Add, contentDescription = "Localized description", modifier = Modifier.size(75.dp))
                    }
                    IconButton(onClick = { navController.navigate("profile") },modifier = Modifier.padding(horizontal = 20.dp)) {
                        Icon(Icons.Filled.Person, contentDescription = "Localized description", modifier = Modifier.size(75.dp))
                    }


},
)
}