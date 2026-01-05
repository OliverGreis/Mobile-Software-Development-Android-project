package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.UserRepository
import kotlinx.coroutines.launch

class CardViewModel(private val userRepository: UserRepository): ViewModel() {
    fun addCard(username: String, cardNumber: String, expiryDate: Int){
        viewModelScope.launch {
            userRepository.addCard(username, cardNumber, expiryDate)
        }
    }

    fun getCard(username: String){
        viewModelScope.launch {
            userRepository.getCards(username)
        }
    }
}