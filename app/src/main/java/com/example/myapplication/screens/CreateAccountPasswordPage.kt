package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.api.UserApiService
import com.example.myapplication.ui.theme.LightGreen
import com.example.myapplication.ui.theme.White
import com.example.myapplication.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateAccountPasswordPage(navController: NavHostController, userApi: UserApiService, userViewModel: UserViewModel)
{
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    val isFormValid by remember {
        derivedStateOf {
            password.isNotBlank() &&
                    confirmPassword.isNotBlank() &&
                    password == confirmPassword
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp,vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(180.dp))

        Text(
            text = "Create a password",
            fontSize = 32.sp,
            //fontFamily = FontFamily(Font(roboto_condensed_regular),
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(45.dp))

        TextField(
            label = "Password",
            placeholder = "Enter password",
            value = password,
            onValueChange = {password = it},
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(30.dp))

        TextField(
            label = "Confirm password",
            placeholder = "Enter password",
            value = confirmPassword,
            onValueChange = {confirmPassword = it},
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(300.dp))

        Button(
            onClick = {
             scope.launch {
                 try {
                     val username = "${userViewModel.username} "

                     userApi.createUser(
                         username = username,
                         email = userViewModel.email,
                         password = password
                     )

                     navController.navigate("add_payment")
                 } catch (e: Exception) {
                     println("Error when creating: ${e.message}")
                 }
             }
            },
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(containerColor = LightGreen, disabledContainerColor = LightGreen),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .height(48.dp)
                .align(Alignment.End)
        )
        {
            Text(
                text = "Next",
                fontSize = 20.sp,
                //fontFamily = FontFamily(Font(roboto_condensed_regular),
                fontWeight = FontWeight.Normal,
                color = White
            )
        }
    }
}

@Composable
fun TextField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color
)
{
    Column(
        modifier = Modifier.fillMaxWidth()
    )
    {
        Text(
            text = label,
            fontSize = 24.sp,
            //fontFamily = FontFamily(Font(roboto_condensed_regular),
            fontWeight = FontWeight.Light,
            color = Color.Black,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(2.dp))

        Surface(
            color = LightGreen,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        )
        {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        }
                        innerTextField()
                    }
                },
                textStyle = androidx.compose.ui.text.TextStyle(
                    color = Color.White,
                    fontSize = 20.sp
                )
            )
        }
    }
}