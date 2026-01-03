package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.SettingPreference
import com.example.myapplication.repository.SettingRepository
import com.example.myapplication.repository.SettingRepositoryImp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.example.myapplication.screens.SettingUiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class SettingViewModel(private val repository: SettingRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingUiState())
    val state: StateFlow<SettingUiState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            runCatching { repository.getInitialSettings() }
                .onSuccess { loaded ->
                    _uiState.value = loaded.copy(isLoading = false, error = null)
                }
                .onFailure { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
        }
    }

    fun toggleNotifications(enabled: Boolean) {

        _uiState.update {
            it.copy(
                notificationsEnabled = enabled,
                // If master is turned off, also turn off group notifications
                groupNotificationsEnabled = if (!enabled) false else it.groupNotificationsEnabled,
                error = null
            )
        }

        viewModelScope.launch {
            runCatching {
                repository.setNotificationsEnabled(enabled)

                // Keep backend consistent: if master off, force group off too
                if (!enabled) repository.setGroupNotificationsEnabled(false)
            }.onFailure { e ->
                // rollback if you want (optional). Here’s a rollback:
                refresh()
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun toggleGroupNotifications(enabled: Boolean) {

        val masterEnabled = _uiState.value.notificationsEnabled
        if (!masterEnabled) return

        _uiState.update { it.copy(groupNotificationsEnabled = enabled, error = null) }

        viewModelScope.launch {
            runCatching {
                repository.setGroupNotificationsEnabled(enabled)
            }.onFailure { e ->
                refresh()
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    companion object {
        fun Factory(pref: SettingPreference) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repo = SettingRepositoryImp(pref)
                    @Suppress("UNCHECKED_CAST")
                    return SettingViewModel(repo) as T
            }
        }
    }

}