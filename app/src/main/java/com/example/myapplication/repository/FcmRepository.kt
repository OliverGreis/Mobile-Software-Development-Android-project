package com.example.myapplication.repository

interface FcmRepository {
    suspend fun registerToken(token: String)
}