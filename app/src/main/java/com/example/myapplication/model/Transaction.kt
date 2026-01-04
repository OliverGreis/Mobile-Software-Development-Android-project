package com.example.myapplication.model

data class Transaction(
    val id: Int,
    val amount: Int,
    val users: List<String>,
    val group: Int,
    val creationDate: String,
    val splitType: String,
    val expenses: List<String>
)
