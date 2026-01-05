package com.example.myapplication.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.api.GroupApiService
import com.example.myapplication.model.Group
import kotlinx.coroutines.launch

class HomeViewModel(
    private val api: GroupApiService,

    ): ViewModel() {
    var groups by mutableStateOf<List<Group>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var groupImageUrl by mutableStateOf<String?>(null)
        private set

    fun loadGroups(userId: String) {
        viewModelScope.launch{
            isLoading = true
            try {
                groups = api.getGroupsForMember(userId)

            } catch (e: Exception) {
                e.printStackTrace()
            }finally {
                isLoading = false
            }
        }
    }

    fun fetchImage(groupId: Int) {
        // This launches a Coroutine in the background
        viewModelScope.launch {
            try {
                // Now you can call the suspend function safely
                val result = api.getGroupImage(groupId)
                groupImageUrl = result
            } catch (e: Exception) {
                Log.e("Error", "Failed to fetch image", e)
            }
        }
    }

    companion object {
        fun Factory(
            groupApi: GroupApiService,
        ) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return HomeViewModel(groupApi) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }

}

