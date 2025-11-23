package com.example.myapplication.data.remote

import android.util.Log
import com.example.myapplication.model.notification.DeviceTokenRequestDTO
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class DeviceTokenApi {

    private val client = KtorClientProvider.client
    private val baseUrl = KtorClientProvider.baseURL

    suspend fun registerDeviceToken(userId: String, token: String) {
        val body = DeviceTokenRequestDTO(userId = userId, token = token)
        Log.w("FCM", "registerDeviceToken body: $body")
        Log.w("FCM", "URL: $baseUrl/api/devicetoken")

        try {
            val response: String = client.put("$baseUrl/api/devicetoken/adddevicetoken") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }.body()

            Log.w("FCM", "registerDeviceToken success: $response")
        } catch (e: Exception) {
            Log.e("FCM", "Error calling /api/devicetoken", e)
        }
    }


    suspend fun testConnection() {
        try {
            val response: String = client.get("$baseUrl/api/devicetoken/test").body()
            Log.w("FCM", "Test endpoint response: $response")
        } catch (e: Exception) {
            Log.e("FCM", "Error calling /api/devicetoken/test", e)
        }
    }
}
