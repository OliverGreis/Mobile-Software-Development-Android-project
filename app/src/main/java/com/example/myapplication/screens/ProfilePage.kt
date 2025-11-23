package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button

@Composable
fun Profile( modifier: Modifier = Modifier) {
    Column(){
        Spacer(Modifier.height(100.dp))
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            ProfileImage()
        }    }
    Text(
        text = "User name ",
        modifier = modifier
    )
    Row(){
        Column {
            Text(
                text = "Email",
                modifier = modifier
            )
            Text(
                text = "UserName@Email.com",
                modifier = modifier
            )
        }
        Column {
            Text(
                text = "Phone Number",
                modifier = modifier
            )
            Text(
                text = "+45 12 34 56 78",
                modifier = modifier
            )
        }
    }


}

@Composable
fun ProfileImage(){
    Card(
        modifier = Modifier
            .size(150.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF4CAF50)
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.icon),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
        }
    }
}


