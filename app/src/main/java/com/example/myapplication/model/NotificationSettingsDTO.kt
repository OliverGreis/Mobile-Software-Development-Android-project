package com.example.myapplication.model

import com.example.myapplication.model.types.NotificationTypes


data class NotificationSettingsDTO (
    val type: NotificationTypes,
    val enabled: Boolean
)