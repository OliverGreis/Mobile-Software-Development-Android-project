package com.example.myapplication.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

@Composable
fun AddAccountPage()
{
    var accountNumber by remember { mutableStateOf("") }
    var accountName by remember { mutableStateOf("") }

    val isAccountValid = remember {
        derivedStateOf {
            accountNumber.isNotBlank() &&
                    accountName.isNotBlank()
        }
    }

    val isValid by remember { derivedStateOf { isAccountValid.value } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
    {
        Spacer(modifier = Modifier.height(120.dp))

        Text(
            text = "Add Account",
            fontSize = 32.sp,
            //fontFamily = FontFamily(Font(roboto_condensed_regular),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        BankSection(
            accountNumber, onAccountNumberChange = { accountNumber = it },
            accountName, onAccountNameChange = { accountName = it },
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