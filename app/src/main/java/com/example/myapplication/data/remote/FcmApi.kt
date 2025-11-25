package com.example.myapplication.data.remote

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable

class FcmApi(
    private val client: HttpClient,
    private val baseUrl: String
) {

    @Serializable
    data class RegisterTokenRequest(
        val token: String
    )

    suspend fun registerToken(token: String) {
        client.post("$baseUrl/fcm/register-token") {
            contentType(ContentType.Application.Json)
            setBody(RegisterTokenRequest(token))
        }
    }
}
