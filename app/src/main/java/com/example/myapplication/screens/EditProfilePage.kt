package com.example.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.example.myapplication.Controller.UserApiService
import com.example.myapplication.ui.theme.LightGreen
import com.example.myapplication.ui.theme.White
import com.example.myapplication.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun EditProfilePage(navController: NavHostController, userApi: UserApiService, userViewModel: UserViewModel)
{
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        try {
            val users = userApi.getUsers()
            val currentUser = users.firstOrNull()
            currentUser?.let {
                val parts = it.username.split(" ")

                userViewModel.firstName = parts.getOrNull(0) ?: ""

                if (parts.size > 2) {
                    userViewModel.middleName = parts.subList(1, parts.size - 1).joinToString(" ")
                    userViewModel.lastName = parts.lastOrNull() ?: ""
                } else {
                    userViewModel.middleName = ""
                    userViewModel.lastName = parts.getOrNull(1) ?: ""
                }
                userViewModel.email = it.email

            }
        } catch (e:Exception)
        {
            println("Error when loading profile: ${e.message}")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(50.dp))

        FormTextField(
            label = "First Name",
            placeholder = "Enter first name",//should be changed to take the info from database
            value = userViewModel.firstName,
            onValueChange = { userViewModel.firstName = it },
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormTextField(
            label = "Middle Name",
            placeholder = "Enter middle name", //should be changed to take the info from database if there are any
            value = userViewModel.middleName,
            onValueChange = { userViewModel.middleName = it },
            isOptional = true,
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormTextField(
            label = "LastName",
            placeholder = "Enter last name", //should be changed to take the info from database
            value = userViewModel.lastName,
            onValueChange = { userViewModel.lastName = it },
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormTextField(
            label = "Email",
            placeholder = "Enter email address", //should be changed to take the info from database
            value = userViewModel.email,
            onValueChange = { userViewModel.email = it },
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormTextField(
            label = "Phone number",
            placeholder = "Enter phone number", //should be changed to take the info from database
            value = userViewModel.phoneNumber,
            onValueChange = { userViewModel.phoneNumber = it },
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(20.dp))


        //should be changed to take the info from database if there are any
        Column()
        {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(start = 4.dp)
            )
            {
                Text(
                    text = "Profie picture",
                    fontSize = 24.sp,
                    //fontFamily = FontFamily(Font(roboto_condensed_regular),
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "optional",
                    fontSize = 10.sp,
                    //fontFamily = FontFamily(Font(roboto_condensed_regular),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 4.dp, top = 6.dp),

                    )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Image(
                painter = painterResource(id = com.example.myapplication.R.drawable.logo),
                contentDescription = "Profile picture",
                modifier = Modifier.size(150.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
               scope.launch {
                   try {
                       userApi.createUser(
                           username = userViewModel.firstName,
                           email = userViewModel.email,
                           password = ""
                       )
                       navController.popBackStack()
                   } catch (e: Exception)
                   {
                       print("weren't able to save chanches: ${e.message}")
                   }
               }
            },
            colors = ButtonDefaults.buttonColors(containerColor = LightGreen, disabledContainerColor = LightGreen),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .height(48.dp)
                .align(Alignment.End)
        )
        {
            Text(
                text = "Save",
                fontSize = 20.sp,
                //fontFamily = FontFamily(Font(roboto_condensed_regular),
                fontWeight = FontWeight.Normal,
                color = White
            )
        }

    }
}