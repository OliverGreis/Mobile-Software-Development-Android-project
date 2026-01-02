package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel()
{
    //user info
    var firstName by mutableStateOf("")
    var middleName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var email by mutableStateOf("")
    var phoneNumber by mutableStateOf("")

    var password by mutableStateOf("")

    //user payment info
    var cardNumber by mutableStateOf("")
    var expiryDate by mutableStateOf("")
    var cvc by mutableStateOf("")
    var accountName by mutableStateOf("")
    var regNumber by mutableStateOf("")
    var accountNumber by mutableStateOf("")
}