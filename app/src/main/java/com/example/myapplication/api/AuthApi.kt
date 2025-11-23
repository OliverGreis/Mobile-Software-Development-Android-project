package com.example.myapplication.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL

object AuthApi {

    private const val BASE_URL = "http://10.0.2.2:8080"

    // REGISTER
    suspend fun register(username: String, email: String, password: String, number: String): String? {
        val endpoint = "/api/users/create/$username/$email/$password/$number"
        return sendRequest("POST", endpoint)
    }

    // LOGIN
    suspend fun login(email: String, password: String): String? {
        val endpoint = "/api/login/$email/$password"
        return sendRequest("POST", endpoint)
    }

    // RESET PASSWORD
    suspend fun reset(email: String, newPassword: String): String? {
        val endpoint = "/api/reset/$email/$newPassword"
        return sendRequest("PUT", endpoint)
    }

    // GENERIC REQUEST
    private suspend fun sendRequest(method: String, endpoint: String): String? {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(BASE_URL + endpoint)
                val connection = url.openConnection() as HttpURLConnection

                connection.requestMethod = method
                connection.doInput = true
                connection.doOutput = false

                val responseCode = connection.responseCode

                val reader = if (responseCode in 200..299) {
                    BufferedReader(connection.inputStream.reader())
                } else {
                    BufferedReader(connection.errorStream?.reader())
                }

                reader?.readText()

            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
