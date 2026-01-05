package com.example.myapplication.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.myapplication.ui.theme.DarkGreen
import com.example.myapplication.viewmodel.AuthViewModel1
import com.example.myapplication.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun AddPaymentPage(
    navController: NavHostController,
    userApi: UserApiService,
    userViewModel: UserViewModel,
    authViewModel: AuthViewModel1
)
{
   val scope = rememberCoroutineScope()

    val isCardValid = remember {
        derivedStateOf {
            userViewModel.cardNumber.isNotBlank() &&
                    userViewModel.expiryDate.isNotBlank() &&
                    userViewModel.cvc.isNotBlank()
        }
    }

    val isBankValid = remember {
        derivedStateOf {
            userViewModel.accountNumber.isNotBlank() &&
                    userViewModel.accountName.isNotBlank()
        }
    }

    val isFormValid by remember {
        derivedStateOf { isCardValid.value || isBankValid.value }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
    {
        Text(
            text = "Add payment method",
            fontSize = 32.sp,
            //fontFamily = FontFamily(Font(roboto_condensed_regular),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "only one needs to be filed (either cart or bank account) to create your account",
            fontSize = 14.sp,
            //fontFamily = FontFamily(Font(roboto_condensed_regular),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        CardSection(
            cardNumber = userViewModel.cardNumber,
            {userViewModel.cardNumber = it},
            expirationDate = userViewModel.expiryDate,
            {userViewModel.expiryDate = it},
            CVC = userViewModel.cvc,
            {userViewModel.cvc = it}

        )

        Spacer(modifier = Modifier.height(20.dp))

        BankSection(
            accountNumber = userViewModel.accountNumber,
            {userViewModel.accountNumber = it},
            accountName = userViewModel.accountName,
            {userViewModel.accountName = it}
        )

        Spacer(modifier = Modifier.height(40.dp))

        CreateButton(
            label = "Create",
            isEnable = isFormValid,
            onClick = {
                scope.launch {
                    try {

                        userApi.createUser(
                            username = userViewModel.username,
                            email = userViewModel.email,
                            password = userViewModel.password
                        )
//                        if (isCardValid)
                        if (true)
                        {
                            userApi.addCard(
                                username = "${userViewModel.username}",
                                cardNumber = userViewModel.cardNumber,
                                expiryDate = userViewModel.expiryDate.toInt(),
                            )
                        }

//                        if (isBankValid )
//                        if (true)
//                        {
//                            userApi.addAccount(
//                                username = "${userViewModel.username} ",
//                                id = 1,
//                                accountname = userViewModel.accountName,
//                                regnum = 0,
//                                accountnumber = userViewModel.accountNumber.toInt()
//                            )
//                        }

                        navController.navigate("home")
                        {
                            popUpTo("login") {inclusive = true}
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
fun CreateButton(
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
        modifier = Modifier
            .height(48.dp)
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



@Composable
fun BankSection(
    accountNumber: String, onAccountNumberChange: (String) -> Unit,
    accountName: String, onAccountNameChange: (String) -> Unit
)
{
    Surface(
        color = LightGreen,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(230.dp)
    )
    {
        Column(
            modifier = Modifier.padding(16.dp))
        {
            Text(
                text = "Bank account",
                fontSize = 24.sp,
                //fontFamily = FontFamily(Font(roboto_condensed_regular),
                fontWeight = FontWeight.Normal,
                color = White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            FormTextField(
                label = "Account number",
                placeholder = "Account number",
                value = accountNumber,
                onValueChange = onAccountNumberChange,
                backgroundColor = DarkGreen
            )

            Spacer(modifier = Modifier.height(15.dp))

            FormTextField(
                label = "Account name",
                placeholder = "Account name",
                value = accountName,
                onValueChange = onAccountNameChange,
                backgroundColor = DarkGreen
            )

        }
    }
}

@Composable
fun CardSection(
    cardNumber: String, onCardNumberChange: (String) -> Unit,
    expirationDate: String, onExpirationDateChange: (String) -> Unit,
    CVC: String, onCVCChange: (String) -> Unit
)
{
    Surface(
        color = LightGreen,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth().
            height(230.dp)
    )
    {
        Column(modifier = Modifier.padding(16.dp))
        {
            Text(
                text = "Card",
                fontSize = 24.sp,
                //fontFamily = FontFamily(Font(roboto_condensed_regular),
                fontWeight = FontWeight.Normal,
                color = White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            FormTextField(
                label = "Card number",
                placeholder = "Cart number",
                value = cardNumber,
                onValueChange = onCardNumberChange,
                backgroundColor = DarkGreen
            )

            Spacer(modifier = Modifier.height(15.dp))

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                {
                    FormTextField(
                        label = "Expiration date",
                        placeholder = "Month/year",
                        value = expirationDate,
                        onValueChange = onExpirationDateChange,
                        backgroundColor = DarkGreen,

                        )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )
                {
                    FormTextField(
                        label = "Security code (CVC)",
                        placeholder = "3 digits",
                        value = CVC,
                        onValueChange = onCVCChange,
                        backgroundColor = DarkGreen
                    )
                }
            }


        }
    }
}

@Composable
fun FormTextField(
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
            fontSize = 16.sp,
            //fontFamily = FontFamily(Font(roboto_condensed_regular),
            fontWeight = FontWeight.Light,
            color = Color.White,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(2.dp))

        Surface(
            color = DarkGreen,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
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
                                fontSize = 16.sp
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