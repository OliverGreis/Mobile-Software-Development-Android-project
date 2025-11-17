package com.onat.backend.transaction.model

import java.time.Instant

data class Transaction(
    val id: Long,
    val amount: Int,
    val groupId: Long,
    val createdBy: String,
    val participants: MutableList<String>,
    val description: String? = null,
    val createdAt: Instant = Instant.now()
)

data class CreateTransactionRequest(
    val amount: Int,
    val groupId: Long,
    val createdBy: String,
    val participants: List<String> = emptyList(),
    val description: String? = null
)

data class AddUserRequest(
    val username: String
)
