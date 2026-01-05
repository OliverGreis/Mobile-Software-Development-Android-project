package com.example.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.UserResponseDTO
import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.repository.UserRepository
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
    ): ViewModel() {

    var currentUser by mutableStateOf<UserResponseDTO?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun login(username: String, password: String, onSuccess: () -> Unit){
        viewModelScope.launch {
            isLoading = true
            try{
                currentUser = authRepository.login(username, password)


                registerDeviceTokenIfPossible()

                onSuccess()
            }catch (e: Exception){
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    fun logout(onSuccess: () -> Unit) {
        currentUser = null
    }

    fun register(username: String, email: String, password: String, number: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            try {
                authRepository.register(username, email, password, number)

                registerDeviceTokenIfPossible()

                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    private suspend fun registerDeviceTokenIfPossible() {
        val userId = currentUser?.userId ?: return
        try {
            val token = FirebaseMessaging.getInstance().token.await()
            userRepository.registerDeviceToken(userId, token)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        fun Factory(authRepo: AuthRepository, userRepo: UserRepository) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return AuthViewModel(authRepo, userRepo) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

}