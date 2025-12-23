package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.LightGreen
import com.example.myapplication.ui.theme.White

@Composable
fun AddCardPage()
{
    var cardNumber by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var CVC by remember { mutableStateOf("") }

    val isCardValid = remember {
        derivedStateOf {
            cardNumber.isNotBlank() &&
                    expirationDate.isNotBlank() &&
                    CVC.isNotBlank()
        }
    }

    val isValid by remember { derivedStateOf { isCardValid.value } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
    {
        Spacer(modifier = Modifier.height(120.dp))

        Text(
            text = "Add card",
            fontSize = 32.sp,
            //fontFamily = FontFamily(Font(roboto_condensed_regular),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        CardSection(
            cardNumber, onCardNumberChange = { cardNumber = it },
            expirationDate, onExpirationDateChange = { expirationDate = it },
            CVC, onCVCChange = { CVC = it }
        )

        Spacer(modifier = Modifier.height(40.dp))

        AddButton(
            label = "Add",
            isEnable = isValid,
            onClick = {},
            modifier = Modifier.align(Alignment.End)
        )

    }
}

@Composable
fun AddButton(
    label: String,
    isEnable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
)
{
    Button(
        onClick = onClick,
        enabled = isEnable,
        colors = ButtonDefaults.buttonColors(containerColor = LightGreen, disabledContainerColor = LightGreen),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.height(48.dp)
    )
    {
        Text(
            text = label,
            fontSize = 24.sp,
            //fontFamily = FontFamily(Font(roboto_condensed_regular),
            fontWeight = FontWeight.Normal,
            color = White
        )
    }
}