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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.ui.theme.LightGreen
import com.example.myapplication.ui.theme.White

@Composable
fun CreateAccountPage(navController: NavHostController) {
    var firstName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }

    val isFormValid by remember {
        derivedStateOf {
            firstName.isNotBlank() &&
                    lastName.isNotBlank() &&
                    email.isNotBlank() &&
                    phoneNumber.isNotBlank()
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
            placeholder = "Enter first name",
            value = firstName,
            onValueChange = {firstName = it},
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormTextField(
            label = "Middle Name",
            placeholder = "Enter middle name",
            value = middleName,
            onValueChange = {middleName = it},
            isOptional = true,
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormTextField(
            label = "Last Name",
            placeholder = "Enter last name",
            value = lastName,
            onValueChange = {lastName = it},
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormTextField(
            label = "Email",
            placeholder = "Enter email address",
            email,
            onValueChange = {email = it},
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(20.dp))

        FormTextField(
            label = "Phone number",
            placeholder = "Enter phone number",
            value = phoneNumber,
            onValueChange = {phoneNumber = it},
            backgroundColor = LightGreen
        )

        Spacer(modifier = Modifier.height(20.dp))

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
                if (isFormValid)
                {
                    navController.navigate("create_account_password")
                }
                else
                {
                    println("fill out first name, last name, email and phone number")
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
fun FormTextField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    isOptional: Boolean = false,
    backgroundColor: Color
)
{
    Column(
        modifier = Modifier.fillMaxWidth()
    )
    {
        Row()
        {
            Text(
                text = label,
                fontSize = 24.sp,
                //fontFamily = FontFamily(Font(roboto_condensed_regular),
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
            if (isOptional) {
                Text(
                    text = "optinal",
                    fontSize = 10.sp,
                    //fontFamily = FontFamily(Font(roboto_condensed_regular),
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 4.dp, top = 6.dp)
                )
            }
        }

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