package com.example.myapplication.api

import com.example.myapplication.api.ApiClient.retrofit
import com.example.myapplication.model.NotificationSettingsDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

val notificationSettingApi: NotificationsSettingApiService = retrofit.create(NotificationsSettingApiService::class.java)
interface NotificationsSettingApiService {

    @GET("/api/notificationsettings/{userId}")
    suspend fun getNotificationsSettings(@Path("userId") userId: String): List<NotificationSettingsDTO>


    @PUT("/api/notificationsettings/update/{userId}/{type}/{enabled}")
    suspend fun updateNotificationsSettings(
        @Path("userId") userId: String,
        @Body notificationSettingsDTO: NotificationSettingsDTO

    )
}