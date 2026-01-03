package com.example.myapplication.repository

import com.example.myapplication.screens.SettingUiState

class SettingRepositoryImp (private val pref: SettingPreference): SettingRepository {
    override suspend fun getInitialSettings(): SettingUiState {
        return SettingUiState(
            notificationsEnabled = pref.getNotificationsEnabled(),
            // add this later when you store it:
            groupNotificationsEnabled = true
        )
    }

    override suspend fun setNotificationsEnabled(enabled: Boolean) {
        pref.setNotificationsEnabled(enabled)
    }
    // add this later when stored TODO
    override suspend fun setGroupNotificationsEnabled(enabled: Boolean) {

    }

}