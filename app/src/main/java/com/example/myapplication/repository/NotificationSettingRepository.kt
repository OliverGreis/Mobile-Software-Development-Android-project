package com.example.myapplication.repository

import com.example.myapplication.api.NotificationsSettingApiService
import com.example.myapplication.model.NotificationSettingsDTO
import com.example.myapplication.model.types.NotificationTypes

class NotificationSettingRepository(
    private val api: NotificationsSettingApiService
) {
    suspend fun update(userId: String, type: NotificationTypes, enabled: Boolean) {
        api.updateNotificationsSettings(
            userId = userId,
            notificationSettingsDTO = NotificationSettingsDTO(
                type = type,
                enabled = enabled
            )
        )
    }
}