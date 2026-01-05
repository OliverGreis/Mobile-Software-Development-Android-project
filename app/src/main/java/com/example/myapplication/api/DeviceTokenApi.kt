package com.example.myapplication.api

import com.example.myapplication.model.DeviceTokenRequestDTO
import retrofit2.http.Body
import retrofit2.http.PUT

interface DeviceTokenApi {
    @PUT("api/devicetoken/adddevicetoken")
    suspend fun registerDeviceToken(@Body body: DeviceTokenRequestDTO): String
}
