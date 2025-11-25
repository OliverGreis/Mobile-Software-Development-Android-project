package com.example.myapplication.services

import android.content.Context
import com.example.myapplication.data.remote.FcmApi
import com.example.myapplication.data.remote.KtorClientProvider
import com.example.myapplication.repository.FcmRepository
import com.example.myapplication.repository.FcmRepositoryImpl

object FcmRepositoryProvider {


    fun provideRepository(context: Context): FcmRepository {
        val api = FcmApi(
            client = KtorClientProvider.client,
            baseUrl = KtorClientProvider.baseURL
        )
        return FcmRepositoryImpl(api)
    }
}
