package com.example.myapplication.repository

import com.example.myapplication.screens.SettingUiState

interface SettingRepository {
    suspend fun getInitialSettings(): SettingUiState
    suspend fun setNotificationsEnabled(enabled: Boolean)
    suspend fun setGroupNotificationsEnabled(enabled: Boolean)
}