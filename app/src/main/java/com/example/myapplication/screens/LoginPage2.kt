package com.example.myapplication.screens

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.LightGreen
import com.example.myapplication.ui.theme.DarkGreen
import com.example.myapplication.ui.theme.White
import com.example.myapplication.navigation.AppNavHost


@Composable
fun LoginPage2(navController: NavController)
{
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Payem",
            fontSize = 40.sp,
            //fontFamily = FontFamily(Font(roboto_condensed_regular),
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(id = com.example.myapplication.R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.size(260.dp)
        )

        Text(
            text = "Log in",
            fontSize = 32.sp,
            //fontFamily = FontFamily(Font(roboto_condensed_regular),
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier= Modifier.height(15.dp))

        InputWithLabel(
            label = "User",
            placeholder = "username",
            value = username,
            onValueChange = {username = it}
        )

        Spacer(modifier = Modifier.height(24.dp))

        InputWithLabel(
            label = "Password",
            placeholder = "Password",
            value = password,
            onValueChange = { password = it}
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Forgot password",
            color = DarkGreen,
            //Font(roboto_condensed_regular,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth().align(Alignment.Start)
                .clickable{navController.navigate("forgot_password")}
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.navigate("create_account") },
            colors = ButtonDefaults.buttonColors(containerColor = LightGreen),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(200.dp)
        )
        {
            Text(
                text = "Log in",
                color = Color.White,
                fontSize = 20.sp,
                //Font(roboto_condensed_regular,
                fontWeight = FontWeight.Normal
            )
        }

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = LightGreen),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.width(200.dp)
        )
        {
            Text(
                text = "Create account",
                fontSize = 20.sp,
                //Font(roboto_condensed_regular,
                fontWeight = FontWeight.Normal
            )
        }



    }
}


@Composable
fun InputWithLabel(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
)
{
    Text(
        text = label,
        fontSize = 24.sp,
        //Font(roboto_condensed_regular,
        fontWeight = FontWeight.Light,
        color = Color.Black,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start
    )

    Spacer(modifier = Modifier.height(4.dp))

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
            decorationBox = {innerTextField ->
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    if (value.isEmpty())
                    {
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