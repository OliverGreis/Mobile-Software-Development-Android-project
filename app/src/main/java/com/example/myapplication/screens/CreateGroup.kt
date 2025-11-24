package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplication7Theme
import androidx.compose.material3.TextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.font.FontWeight
import com.example.myapplication.R
import java.time.format.TextStyle
@Composable
fun CreateGroup(modifier: Modifier = Modifier) {

    Column(){
        Spacer(Modifier.height(100.dp))

        Text(
            text = "Group Name",
            modifier = modifier
        )

        /*TextField(
            state = rememberTextFieldState("Hello\nWorld\nInvisible"),
            lineLimits = TextFieldLineLimits.MultiLine(maxHeightInLines = 2),
            placeholder = { Text("") },
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            label = { Text("Enter text") },
            modifier = Modifier.padding(20.dp)
        )*/

        Text(
            text = "Group Image",
            modifier = modifier
        )

        Text(
            text = "Add Members",
            modifier = modifier
        )

        LazyRow(  contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {

            items(7){
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .size(90.dp)
                        .background(Color(0xFF8BC34A), RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF8BC34A)
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.icon),
                            contentDescription = "Member icon",
                            modifier = Modifier.size(40.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                }
            }
    }
    }
}

