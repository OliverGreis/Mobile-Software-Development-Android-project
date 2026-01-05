package com.example.myapplication.repository

import android.content.Context
import androidx.core.content.edit

class SettingPreference(context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun getNotificationsEnabled(): Boolean =
        prefs.getBoolean("notificationsEnabled", true)

    fun setNotificationsEnabled(enabled: Boolean) {
        prefs.edit { putBoolean("notificationsEnabled", enabled) }
    }

    fun getGroupNotificationsEnabled(): Boolean =
        prefs.getBoolean("groupNotificationsEnabled", true)

    fun setGroupNotificationsEnabled(enabled: Boolean) {
        prefs.edit { putBoolean("groupNotificationsEnabled", enabled) }
    }
}