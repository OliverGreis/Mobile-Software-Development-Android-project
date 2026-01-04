package com.example.myapplication.model

data class Group(
    val id: Int,
    val name: String,
    val memberIDs: List<String>,
    val transactionIDs: List<Int>,
    val creationDate: String,
    val groupImage: String
)
