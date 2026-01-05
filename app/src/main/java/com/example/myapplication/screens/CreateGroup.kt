package com.example.myapplication.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.TextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.R.font.roboto_condensed_bold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.TextFieldDefaults
import androidx.navigation.NavHostController
import com.example.myapplication.viewmodel.GroupViewModel


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun CreateGroup(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    groupViewModel: GroupViewModel,
) {
    var textName by remember { mutableStateOf("") }

    var memberInput by remember { mutableStateOf("") }
    var memberUsernames by remember { mutableStateOf(listOf<String>()) }


    Column(){
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
            value = memberInput,
            onValueChange = { memberInput = it },
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

            items(memberUsernames.size){ index ->
                val username = memberUsernames[index]

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
                val cleaned = memberInput.trim()
                if (cleaned.isNotBlank() && cleaned !in memberUsernames) {
                    memberUsernames = memberUsernames + cleaned
                    memberInput = ""
                }
                Log.e("memberUsernames", memberUsernames.toString())
            },
            modifier = Modifier
                .padding(horizontal = 135.dp)
                .padding(vertical = 25.dp),
            colors = ButtonDefaults.buttonColors(
                Color(android.graphics.Color.parseColor("#88C25F"))
            )
        ) {
            Text("Add member")
        }

        Button(
            onClick = {
                groupViewModel.createGroupAndAddMembers(
                    groupName = textName,
                    usernames = memberUsernames,
                    onSuccess = {
                    navController.navigate("home?refresh=true"){
                        popUpTo("createGroup"){inclusive = true}
                    }
                })
            },
            modifier = Modifier
                .padding(horizontal = 135.dp)
                .padding(vertical = 25.dp),
            colors = ButtonDefaults.buttonColors(
                Color(android.graphics.Color.parseColor("#88C25F"))
            )
        ) {
            Text("Create Group")
        }
    }
}







