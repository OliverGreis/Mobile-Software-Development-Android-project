package com.example.myapplication.data.remote

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object KtorClientProvider {

    val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = false
                    isLenient = true
                    encodeDefaults = true
                }
            )
        }
    }
    // I'm using phone if emulator use 10.0.2.2/8080
    const val baseURL = "http://192.168.1.94:8080"
}
