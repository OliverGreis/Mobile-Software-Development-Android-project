package com.example.myapplication.Controller
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ControllerHelperMethods(
    private val groupApi: GroupApiService,
    private val userApi: UserApiService,
    private val transactionApi: TransactionApiService,
    private val expenseApi: ExpenseApiService
) {
    fun extractDigitsResponse(response: String): Int? {
        val regex = Regex("\\d+")
        val find = regex.find(response)
        return find?.value?.toInt()
    }

    suspend fun getUsersFromTransaction(transactionID: Int): List<String>? {
        return try {
            val transaction = transactionApi.getTransaction(transactionID).firstOrNull()
            transaction?.users
        } catch (e: Exception) {
            null
        }
    }

    suspend fun addUserToGroup(userID: String, groupID: Int, username: String): Boolean {
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

    suspend fun removeUserFromGroup(userID: String, groupID: Int, username: String): Boolean {
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

    suspend fun createTransaction(amount: Int, username: String, groupID: Int): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val transactionResponse = transactionApi.createTransaction(amount, username, groupID)
                val transactionID = extractDigitsResponse(transactionResponse)?.toInt()

                if (transactionID != null) {
                    val groupResponse = groupApi.addTransaction(transactionID, groupID)
                    val userResponse = userApi.addTransaction(transactionID, username)

                    groupResponse.contains("added") && userResponse.contains("Added") && transactionResponse.contains("created")
                } else {
                    false
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    suspend fun removeTransaction(id: Int, groupID: Int) {
        return withContext(Dispatchers.IO) {
            try {
                val users = getUsersFromTransaction(id)
                val groupResponse = groupApi.removeTransaction(id, groupID)
                val userResponses = ArrayList<String>()
                if (users != null) {
                    for (user in users) {
                        userResponses.add(userApi.removeTransaction(id, user))
                    }
                    groupResponse.contains("removed") && userResponses.firstOrNull()?.contains("Removed") == true
                } else {
                    false
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }
}