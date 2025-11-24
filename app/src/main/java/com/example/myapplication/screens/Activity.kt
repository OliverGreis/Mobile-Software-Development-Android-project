package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ActivityPage(modifier: Modifier = Modifier) {

    Column(){
    Spacer(Modifier.height(100.dp))
    Text(
        text = "Activity: ",
        modifier = modifier
        )
        LazyColumn(modifier = Modifier.height(600.dp)) {
        items(7) { index ->
        ActivityPageItem()
        }
 }
}
}

    @Composable
    fun ActivityPageItem() {
        Card(
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