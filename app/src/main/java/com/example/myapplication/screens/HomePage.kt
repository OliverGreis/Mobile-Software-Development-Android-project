package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R

@Composable
fun Home(modifier: Modifier = Modifier,navController: NavHostController) {
Column(){
    Spacer(Modifier.height(100.dp))

    Text(
        text = "Groups:",
        modifier = modifier
    )
    LazyColumn(modifier = Modifier.height(300.dp)) {
        items(7) { index ->
            GroupItem(onClick = { navController.navigate("group") })
        }
    }
    Text(
            text = "Activity: ",
            modifier = modifier
        )
        LazyColumn(modifier = Modifier.height(300.dp)) {
            items(7) { index ->
                HomeActivityItem(onClick = { navController.navigate("activity") })
            }
}
}
}

@Composable
fun HomeActivityItem(onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .width(400.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1B5E20)
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {


        Text("+25,00 kr", color = Color.White)

        Spacer(Modifier.width(12.dp))



            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text("From Vaccation Time", color = Color.White)
                Text("Member Name", color = Color.White)

                Spacer(Modifier.height(6.dp))

            }
        }
    }


@Composable
fun GroupItem(onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .width(400.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF1B5E20)
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier.size(55.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(R.drawable.icon),
                        contentDescription = null,
                        modifier = Modifier.size(55.dp)
                    )
                }
            }

            Spacer(Modifier.width(12.dp))


            Text("Alex Birthday", color = Color.White)

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text("Missing", color = Color.White)
                Text("Payment", color = Color.White)

                Spacer(Modifier.height(6.dp))

            }
        }
    }
}
