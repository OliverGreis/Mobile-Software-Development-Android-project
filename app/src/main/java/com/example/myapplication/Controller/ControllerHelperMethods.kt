package com.example.myapplication.Controller
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ControllerHelperMethods(
    private val groupApi: GroupApiService,
    private val userApi: UserApiService
) {
    suspend fun addUserToGroup(userID: String, groupID: Integer, username: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val groupResponse = groupApi.addMember(userID, groupID)
                val userResponse = userApi.addGroup(groupID, username)

                groupResponse.contains("added") && userResponse.contains("Added")
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}