package com.example.myapplication.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.GroupRepository
import kotlinx.coroutines.launch

data class CreateGroupUi(
    val name: String = "",
    val members: String = "",
    val isLoadings: Boolean = false,
)

class GroupViewModel (private val groupRepository: GroupRepository): ViewModel(){
    var state  by mutableStateOf(CreateGroupUi())
        private set


    fun onNameChange(name: String){
        state = state.copy(name = name)
    }

    fun onMemberChange(v: String){
        state = state.copy(members = v)
    }


    fun createGroupAndAddMembers(
        groupName: String,
        usernames: List<String>,
        onSuccess: () -> Unit,
        onError: (String) -> Unit = {}
    ){
        if(groupName.isEmpty()){
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoadings = true)
            try {

                val created = groupRepository.createGroup(groupName)
                val groupId = parseGroupId(created)

                usernames
                    .map{it.trim()}
                    .filter { it.isNotEmpty() }
                    .distinct()
                    .forEach {
                        username ->
                        Log.e("username", username)
                        Log.e("groupId", groupId.toString())
                        groupRepository.addMember(
                            groupId = groupId,
                            username = username
                        )
                    }
                onSuccess()
            }catch (e: Exception){
                onError(e.message ?: "Failed to create group")
            }finally {
                state = state.copy(isLoadings = false)
            }

        }
    }

    class Factory(private val repo: GroupRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GroupViewModel(repo) as T
        }
    }
}

private fun parseGroupId(message: String): Int {
    // "Group created with id: 123"
    return Regex("""\d+""").find(message)?.value?.toInt()
        ?: throw IllegalStateException("Could not parse group id from: $message")
}