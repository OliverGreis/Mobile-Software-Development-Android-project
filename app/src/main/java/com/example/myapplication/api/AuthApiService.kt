package com.example.myapplication.api

import com.example.myapplication.api.ApiClient.retrofit
import com.example.myapplication.model.LoginRequestDTO
import com.example.myapplication.model.UserResponseDTO
import com.example.myapplication.model.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

val authApi: AuthApiService = retrofit.create(AuthApiService::class.java)
interface AuthApiService {
    @POST("api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequestDTO): UserResponseDTO

    @POST("api/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): UserResponseDTO

}