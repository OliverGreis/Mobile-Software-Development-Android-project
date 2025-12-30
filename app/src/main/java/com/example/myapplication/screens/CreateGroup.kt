package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.R.font.roboto_condensed_bold
import java.time.format.TextStyle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.TextFieldDefaults
import com.example.myapplication.Controller.groupApi
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import java.net.URLEncoder
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun CreateGroup(navController: NavHostController,modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    var textName by remember { mutableStateOf("") }
    var textMembers by remember { mutableStateOf("") }
    Column(){
        Spacer(Modifier.height(100.dp))

        Text(
            text = "Group Name",
            color = Color.Black,
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(roboto_condensed_bold)),
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        TextField(
            value = textName,
            onValueChange = { textName = it },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(8.dp))
                .width(380.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
                unfocusedContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
                disabledContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
                errorContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
            )
        )

        Text(
            text = "Group Image",
            color = Color.Black,
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(roboto_condensed_bold)),
            modifier = Modifier.padding(horizontal = 20.dp)

        )

        Box(
            modifier = Modifier
                .height(150.dp)
                .width(420.dp)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(android.graphics.Color.parseColor("#104210"))),
        )

        Text(
            text = "Add Members",
            color = Color.Black,
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(roboto_condensed_bold)),
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        TextField(
            value = textMembers,
            onValueChange = { textMembers = it },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(8.dp))
                .width(380.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
                unfocusedContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
                disabledContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
                errorContainerColor = Color(android.graphics.Color.parseColor("#88C25F")),
            )
        )
        Text(
            text = "Members Added",
            color = Color.Black,
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(roboto_condensed_bold)),
            modifier = Modifier.padding(horizontal = 20.dp)

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
                            painter = painterResource(R.drawable.profile_picture),
                            contentDescription = "Member icon",
                            modifier = Modifier.size(40.dp),
                            contentScale = ContentScale.Fit
                        )
                    }

                }
            }
    }
        Button(
            onClick = {
                coroutineScope.launch {
                    try {
                        val response = groupApi.createGroup(URLEncoder.encode(textName, "UTF-8"))
                        println(response)
                        val updatedGroups = groupApi.getGroups()
                        navController.navigate("home?refresh=true"){
                            popUpTo("createGroup") { inclusive = true }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            },
            modifier = Modifier
                .padding(horizontal = 135.dp)
                .padding(vertical = 25.dp),
            colors = ButtonDefaults.buttonColors(
                Color(android.graphics.Color.parseColor("#88C25F"))
            )
        ) {
            Text("Make Request")
        }
    }
}







