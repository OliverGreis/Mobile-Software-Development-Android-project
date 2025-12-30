package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R.font.roboto_condensed_bold

@Composable
fun ActivityPage(modifier: Modifier = Modifier) {

    Column(){
    Spacer(Modifier.height(100.dp))
        Text(
            text = "Activity:",
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(roboto_condensed_bold)),
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Box(
            modifier = Modifier
                .height(600.dp)
                .padding(horizontal = 20.dp)
                .width(380.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(android.graphics.Color.parseColor("#88C25F"))),
        ){
        LazyColumn(modifier = Modifier.height(600.dp)) {
        items(20) { index ->
        ActivityPageItem()
        }
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
            containerColor = Color(android.graphics.Color.parseColor("#88C25F")),
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {


        Row() {
            Text("+25,00 kr", color = Color.White)

            Spacer(Modifier.width(75.dp))



            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text("From Vaccation Time", color = Color.White)
                Text("Member Name", color = Color.White)

                Spacer(Modifier.height(6.dp))

            }
        }
    }
}