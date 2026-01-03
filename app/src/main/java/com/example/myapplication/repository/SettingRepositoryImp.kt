package com.example.myapplication.repository

import com.example.myapplication.data.remote.NotificationSettingApi
import com.example.myapplication.model.NotificationSettingsDTO
import com.example.myapplication.model.types.NotificationTypes
import com.example.myapplication.screens.SettingUiState

class SettingRepositoryImp (
    private val pref: SettingPreference,
    private val remote: NotificationSettingApi
): SettingRepository{
    override suspend fun getInitialSettings(): SettingUiState {
        return SettingUiState(
            notificationsEnabled = pref.getNotificationsEnabled(),
            groupNotificationsEnabled = pref.getGroupNotificationsEnabled()
        )
    }

    override suspend fun setNotificationsEnabled(userId: String, enabled: Boolean) {
        remote.update(
            userId = userId,
            type = NotificationTypes.GENERAL_NOTIFICATION,
            enabled = enabled
        )

        //Cache
        pref.setNotificationsEnabled(enabled)
    }
    // add this later when stored TODO
    override suspend fun setGroupNotificationsEnabled(enabled: Boolean) {

    }

}