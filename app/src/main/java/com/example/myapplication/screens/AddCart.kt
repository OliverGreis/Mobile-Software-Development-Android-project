package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CardPage(modifier: Modifier = Modifier) {

    Column(){
        Spacer(Modifier.height(100.dp))
        Text(
            text = "Add card: ",
            modifier = modifier
        )
        LazyColumn(modifier = Modifier.height(600.dp)) {
            items(7) { index ->
                ActivityPageItem()
            }
        }
    }
}