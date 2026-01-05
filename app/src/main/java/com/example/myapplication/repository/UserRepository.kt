package com.example.myapplication.repository

import com.example.myapplication.api.UserApiService
import com.example.myapplication.model.Card

class UserRepository(private val userApiService: UserApiService) {
    suspend fun addCard(username: String, cardNumber: String, expiryDate: Int) {
        userApiService.addCard(username, cardNumber, expiryDate)
    }

    suspend fun getCards(username: String): List<Card> {
        return userApiService.getCards(username)
    }
}
