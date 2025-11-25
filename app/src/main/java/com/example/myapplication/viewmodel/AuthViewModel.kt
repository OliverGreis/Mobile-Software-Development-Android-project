package com.example.myapplication.viewmodel
import android.app.Activity
import com.example.myapplication.repository.AuthRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.myapplication.model.AuthUiState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class AuthViewModel(private val repo: AuthRepository) : ViewModel() {
    val uiState = MutableStateFlow(AuthUiState())

    fun login(activity: Activity) = viewModelScope.launch{
        uiState.update {it.copy(isLoading = true)}
        try {
            val user = repo.login(activity)
            uiState.update {it.copy(isLoading = false, isLoggedIn = true, user = user)}
        }catch(e: Exception){
            uiState.update {it.copy(isLoading = false, error = e.message)}
        }
    }

    fun logout (activity: Activity) = viewModelScope.launch{
        repo.logout(activity)
        uiState.value = AuthUiState()

    }
}