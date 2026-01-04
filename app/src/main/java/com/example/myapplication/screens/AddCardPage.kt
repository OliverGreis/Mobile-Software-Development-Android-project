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
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.Controller.UserApiService
import com.example.myapplication.ui.theme.LightGreen
import com.example.myapplication.ui.theme.White
import com.example.myapplication.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun AddCardPage(navController: NavHostController, userApi: UserApiService, userViewModel: UserViewModel)
{
    val scope = rememberCoroutineScope()

    val isCardValid = remember {
        derivedStateOf {
            userViewModel.cardNumber.isNotBlank() &&
                    userViewModel.expiryDate.isNotBlank() &&
                    userViewModel.cvc.isNotBlank()
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
            text = "Add card",
            fontSize = 32.sp,
            //fontFamily = FontFamily(Font(roboto_condensed_regular),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        CardSection(
            cardNumber = userViewModel.cardNumber,
            onCardNumberChange = {userViewModel.cardNumber = it},
            expirationDate = userViewModel.expiryDate,
            onExpirationDateChange = {userViewModel.expiryDate = it},
            CVC = userViewModel.cvc,
            onCVCChange = {userViewModel.cvc = it}
        )

        Spacer(modifier = Modifier.height(40.dp))

        AddButton(
            label = "Add",
            isEnable = isCardValid,
            onClick = {
                scope.launch { 
                    try {
                        userApi.addCard(
                            username = "${userViewModel.firstName} ${userViewModel.lastName}",
                            cardname = userViewModel.cardNumber,
                            expiryDate = userViewModel.expiryDate,
                            cvc = userViewModel.cvc
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

@Composable
fun AddButton(
    label: String,
    isEnable: State<Boolean>,
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