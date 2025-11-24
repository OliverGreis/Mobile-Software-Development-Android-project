package com.example.myapplication.repository

import com.example.myapplication.data.remote.FcmApi

class FcmRepositoryImpl(
    private val api: FcmApi
) : FcmRepository {

    override suspend fun registerToken(token: String) {
        try {
            api.registerToken(token)
        } catch (e: Exception) {
            // log or ignore for now
            e.printStackTrace()
        }
    }
}
