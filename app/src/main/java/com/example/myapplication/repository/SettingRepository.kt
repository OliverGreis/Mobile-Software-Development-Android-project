package com.example.myapplication.repository

import com.example.myapplication.screens.SettingUiState

interface SettingRepository {
    suspend fun getInitialSettings(): SettingUiState
    suspend fun setNotificationsEnabled(userId: String, enabled: Boolean)
    suspend fun setGroupNotificationsEnabled(enabled: Boolean)
}