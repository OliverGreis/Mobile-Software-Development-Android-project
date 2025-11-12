package com.example.myapplication.repository

import android.app.Activity
import com.example.myapplication.model.User

interface AuthRepository {
    suspend fun login(activity: Activity): User
    suspend fun logout(activity: Activity)
}


