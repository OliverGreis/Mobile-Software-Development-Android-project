package com.example.myapplication.screens
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R.font.roboto_condensed_bold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CreateTransaction(navController: NavHostController,modifier: Modifier = Modifier) {
    var textName by remember { mutableStateOf("") }

    Column() {


        Text(
            text = "Enter payment ammount",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(roboto_condensed_bold)),
            textAlign = TextAlign.Center
        )
        TextField(
            value = textName,
            onValueChange = { textName = it },
            modifier = Modifier
                .padding(horizontal = 50.dp, vertical = 40.dp)
                .clip(RoundedCornerShape(8.dp))
                .width(300.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
                unfocusedContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
                disabledContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
                errorContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
            )
        )

    }
    }