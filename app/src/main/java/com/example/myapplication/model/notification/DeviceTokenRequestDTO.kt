package com.example.myapplication.model.notification

import kotlinx.serialization.Serializable

@Serializable
data class DeviceTokenRequestDTO(
    val userId: String,
    val token: String
)
