package com.example.myapplication.Controller

import com.example.myapplication.model.NotificationSettingsDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface NotificationsSettingApiController {

    @GET("/api/notificationsettings/{userId}")
    suspend fun getNotificationsSettings(@Path("userId") userId: String): List<NotificationSettingsDTO>


    @PUT("/api/notificationsettings/update/{userId}/{type}/{enabled}")
    suspend fun updateNotificationsSettings(
        @Path("userId") userId: String,
        @Body notificationSettingsDTO: NotificationSettingsDTO

    )
}