package com.example.myapplication.Controller
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ControllerHelperMethods(
    private val groupApi: GroupApiService,
    private val userApi: UserApiService,
    private val transactionApi: TransactionApiService,
    private val expenseApi: ExpenseApiService
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

    suspend fun removeUserFromGroup(userID: String, groupID: Integer, username: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val groupResponse = groupApi.removeMember(userID, groupID)
                val userResponse = userApi.removeGroup(groupID.toInt(), username)

                groupResponse.contains("removed") && userResponse.contains("Removed")
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}