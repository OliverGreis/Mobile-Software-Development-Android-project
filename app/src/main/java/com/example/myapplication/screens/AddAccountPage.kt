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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.Controller.UserApiService
import com.example.myapplication.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun AddAccountPage(navController: NavHostController, userApi: UserApiService, userViewModel: UserViewModel)
{
    val scope = rememberCoroutineScope()

    val isAccountValid = remember {
        derivedStateOf {
            userViewModel.accountNumber.isNotBlank() &&
                    userViewModel.accountName.isNotBlank()
        }
    }

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
            accountNumber = userViewModel.accountNumber,
            onAccountNumberChange = { userViewModel.accountNumber = it },
            accountName = userViewModel.accountName,
            onAccountNameChange = { userViewModel.accountName = it },
        )

        Spacer(modifier = Modifier.height(40.dp))

        AddButton(
            label = "Add",
            isEnable = isAccountValid,
            onClick = {
                scope.launch {
                    try {
                        userApi.addAccount(
                            username = "${userViewModel.firstName} ${userViewModel.lastName}",
                            id = 0,
                            accountname = "",
                            regnum = 0,
                            accountnumber = 0
                        )

                        navController.navigate("profile")
                        {
                            popUpTo("profile") {inclusive = true}
                        }
                    } catch (e: Exception)
                    {
                        e.printStackTrace()
                    }
                }
            },
            modifier = Modifier.align(Alignment.End)
        )

    }
}