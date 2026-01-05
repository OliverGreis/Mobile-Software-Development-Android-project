package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.api.UserApiService
import com.example.myapplication.api.DeviceTokenApi
import com.example.myapplication.model.Card
import com.example.myapplication.model.DeviceTokenRequestDTO

class UserRepository(private val userApiService: UserApiService, private val deviceTokenApi: DeviceTokenApi) {
    suspend fun addCard(username: String, cardNumber: String, expiryDate: Int) {
        userApiService.addCard(username, cardNumber, expiryDate)
    }

    suspend fun getCards(username: String): List<Card> {
        return userApiService.getCards(username)
    }

    suspend fun registerDeviceToken(rawUserId: String, token: String) {
        val trimmedUserId = rawUserId.substringAfter("|")
        val body = DeviceTokenRequestDTO(userId = trimmedUserId, token = token)
        Log.w("FCM", "registerDeviceToken body: $body")

        try {
            val response = deviceTokenApi.registerDeviceToken(body)
            Log.w("FCM", "registerDeviceToken success: $response")
        } catch (e: Exception) {
            Log.e("FCM", "registerDeviceToken failed", e)
            throw e
        }
    }
}
