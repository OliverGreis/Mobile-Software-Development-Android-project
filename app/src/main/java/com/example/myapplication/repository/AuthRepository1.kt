package com.example.myapplication.repository

import com.example.myapplication.api.AuthApiService
import com.example.myapplication.model.LoginRequestDTO
import com.example.myapplication.model.RegisterRequest
import com.example.myapplication.model.UserResponseDTO

class AuthRepository1(private val authApiService: AuthApiService) {
    suspend fun login(email: String, password: String): UserResponseDTO{
        return authApiService.login(LoginRequestDTO(email, password) )
    }

    suspend fun register(username: String, email: String, password: String, number: String): UserResponseDTO{
        return authApiService.register(RegisterRequest(username, email, password, number))
    }
}
