package com.example.myapplication.data.remote

import com.example.myapplication.Controller.NotificationsSettingApiController
import com.example.myapplication.model.NotificationSettingsDTO
import com.example.myapplication.model.types.NotificationTypes

class NotificationSettingApi(
    private val api: NotificationsSettingApiController
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
