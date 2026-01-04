package com.example.myapplication.model

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val number: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class ResetRequest(
    val email: String,
    val newPassword: String
)
