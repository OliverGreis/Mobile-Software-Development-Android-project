package com.example.myapplication.model

data class ResetRequest(
    val email: String,
    val newPassword: String
)
