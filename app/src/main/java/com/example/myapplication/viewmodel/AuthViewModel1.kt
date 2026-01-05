package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.UserResponseDTO
import com.example.myapplication.repository.AuthRepository1
import kotlinx.coroutines.launch

class AuthViewModel1(private val authRepository: AuthRepository1): ViewModel() {

    var currentUser by mutableStateOf<UserResponseDTO?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun login(email: String, password: String, onSuccess: () -> Unit){
        viewModelScope.launch {
            isLoading = true
            try{
                currentUser = authRepository.login(email, password)
                onSuccess()
            }catch (e: Exception){
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    fun register(username: String, email: String, password: String, number: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            try {
                authRepository.register(username, email, password, number)
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    fun logout(onSuccess: () -> Unit) {
        currentUser = null
    }

    companion object {
        fun Factory(authRepo: AuthRepository1) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(AuthViewModel1::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return AuthViewModel1(authRepo) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

}