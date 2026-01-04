package com.example.myapplication.model

data class User(
    val userId: String,
    val username: String,
    val email: String,
    val password: String,
    val groupsMember: List<Int>,
    val transactionsMember: List<Int>,
    val cards: List<Card>,
    val accounts: List<Account>,
    val profileImage: String,
    val phoneNumber: Int
)