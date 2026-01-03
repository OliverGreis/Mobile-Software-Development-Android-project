package com.example.myapplication.Controller
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.PUT


data class Group(
    val id: Int,
    val name: String,
    val memberIDs: List<String>,
    val transactionIDs: List<Int>,
    val creationDate: String
)

data class User(
    val userId: String,
    val username: String,
    val email: String,
    val password: String,
    val groupsMember: List<Int>,
    val transactionsMember: List<Int>,
    val cards: List<Card>,
    val accounts: List<Account>
)

data class Transaction(
    val id: Int,
    val amount: Int,
    val users: List<String>,
    val group: Int,
    val creationDate: String,
    val splitType: String,
    val expenses: List<String>
)

data class Expense(
    val expenseID: String,
    val username: String,
    val amount: Int,
    val transactionID: Int
)

data class Card(
    val id: Int,
    val cardNumber: Int,
    val expiryDate: Int
)

data class Account(
    val id: Int,
    val accountName: String,
    val regNum: Int,
    val accountNumber: Int
)

private const val BASE_URL =
    "http://10.0.2.2:8080/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

val groupApi: GroupApiService = retrofit.create(GroupApiService::class.java)
val userApi: UserApiService = retrofit.create(UserApiService::class.java)
val transactionApi: TransactionApiService = retrofit.create(TransactionApiService::class.java)
val expenseApi: ExpenseApiService = retrofit.create(ExpenseApiService::class.java)


interface GroupApiService {
    @GET("api/groups")
    suspend fun getGroups(): List<Group>

    @POST("api/creategroup/{name}")
    suspend fun createGroup(@Path("name") name: String): Group

    @GET("api/groups/member/{id}")
    suspend fun getGroupsForMember(@Path("id") id: String): List<Group>

    @PUT("api/addmember/{id}/{groupID}")
    suspend fun addMember(@Path("id") id: String, @Path("groupID") groupID: Int): String

    @PUT("api/addtransaction/{id}/{groupID}")
    suspend fun addTransaction(@Path("id") id: Int, @Path("groupID") groupID: Int): String

    @GET("api/group/{id}")
    suspend fun getGroup(@Path("id") id: String): String

    @PUT("api/removemember/{id}/{groupID}")
    suspend fun removeMember(@Path("id") id: String, @Path("groupID") groupID: Int): String

    @PUT("api/removetransaction/{id}/{groupID}")
    suspend fun removeTransaction(@Path("id") id: Int, @Path("groupID") groupID: Int): String

    @GET("api/group/notify/{id}")
    suspend fun notifyGroupPing(@Path("id") id: Int): String
}

interface UserApiService {
    @GET("api/users")
    suspend fun getUsers(): List<User>

    @GET("api/users/{username}/history")
    suspend fun getUserHistory(@Path("username") username: String): List<Integer>

    @POST("api/users/create/{username}/{email}/{password}")
    suspend fun createUser(@Path("username") username: String,
                           @Path("email") email: String, @Path("password") password: String): String

    @PUT("api/user/addgroup/{id}/{username}")
    suspend fun addGroup(@Path("id") id: Int, @Path("username") username: String): String

    @PUT("api/user/addtransaction/{id}/{username}")
    suspend fun addTransaction(@Path("id") id: Int, @Path("username") username: String): String

    @PUT("api/user/removegroup/{id}/{username}")
    suspend fun removeGroup(@Path("id") id: Int, @Path("username") username: String): String

    @PUT("api/user/removetransaction/{id}/{username}")
    suspend fun removeTransaction(@Path("id") id: Int, @Path("username") username: String): String

    @PUT("api/user/setuserid/{username}/{userId}")
    suspend fun setUserId(@Path("username") username: String, @Path("userId") userId: String): String

    @GET("api/user/{username}")
    suspend fun getUserByUsername(@Path("username") username: String): User

    @PUT("api/user/addcard/{username}/{id}/{cardnumber}/{expirydate}")
    suspend fun addCard(@Path("username") username: String, @Path("id") id: Int,
                        @Path("cardnumber") cardNumber: Int, @Path("expirydate") expiryDate: Int): String

    @PUT("api/user/removecard/{username}/{id}")
    suspend fun removeCard(@Path("username") username: String, @Path("id") id: Int): String

    @PUT("api/user/addaccount/{username}/{id}/{accountname}/{regnum}/{accountnumber}")
    suspend fun addAccount(@Path("username") username: String, @Path("id") id: Int,
                           @Path("accountname") accountname: String, @Path("regnum") regnum: Int,
                           @Path("accountnumber") accountnumber: Int): String

    @PUT("api/user/removeaccount/{username}/{id}")
    suspend fun removeAccount(@Path("username") username: String, @Path("id") id: Int): String
}

interface TransactionApiService {
    @GET("api/transactions/group/{id}")
    suspend fun getTransactionsGroup(@Path("id") id: Int): List<Transaction>

    @GET("api/transactions/user/{username}")
    suspend fun getTransactionsUser(@Path("username") username: String): List<Transaction>

    @GET("api/transactions/{id}")
    suspend fun getTransaction(@Path("id") id: Int): List<Transaction>

    @POST("api/transactions/create/{amount}/{username}/{group}")
    suspend fun createTransaction(@Path("amount") amount: Int,
                                  @Path("username") username: String, @Path("group") group: Int): String

    @PUT("api/transactions/adduser/{id}/{username}")
    suspend fun addUserTransaction(@Path("id") id: Int, @Path("username") username: String): String

    @PUT("api/transactions/removeuser/{id}/{username}")
    suspend fun removeUserTransaction(@Path("id") id: Int, @Path("username") username: String): String

    @GET("api/transactions/getsplittype/{id}")
    suspend fun getSplitType(@Path("id") id: Int): String

    @PUT("api/transactions/setsplittype/{id}/{string}")
    suspend fun setSplitType(@Path("id") id: Int, @Path("string") string: String): String

    @GET("api/transactions/expenses/{id}")
    suspend fun getExpensesTransaction(@Path("id") id: Int): List<String>

    @PUT("api/transactions/addexpenses/{id}/{expense}")
    suspend fun addExpense(@Path("id") id: Int, @Path("expense") expense: String): String

    @PUT("api/transactions/removeexpenses/{id}/{expense}")
    suspend fun removeExpense(@Path("id") id: Int, @Path("expense") expense: String): String

    @GET("api/transactions/getpaidstatus/{id}")
    suspend fun getPaidStatus(@Path("id") id: Int): String

    @PUT("api/transactions/setpaidstatus/{id}/{value}")
    suspend fun setPaidStatus(@Path("id") id: Int, @Path("value") value: Boolean): String
}

interface ExpenseApiService {
    @POST("api/createexpense/{id}/{username}/{amount}/{transactionID}")
    suspend fun createExpense(@Path("id") id: String, @Path("username") username: String,
                              @Path("amount") amount: Int, @Path("transactionID") transactionID: Int): String

    @GET("api/expense/{id}")
    suspend fun getExpense(@Path("id") id: String): String

    @PUT("api/expense/{id}/changeamount/{amount}")
    suspend fun changeAmount(@Path("id") id: String, @Path("amount") amount: Int): String

    @PUT("api/expense/{id}/changeuser/{username}")
    suspend fun changeUser(@Path("id") id: String, @Path("username") username: String): String

    @GET("api/expense/getpaidstatus/{id}")
    suspend fun getPaidStatus(@Path("id") id: String): String

    @PUT("api/expense/setpaidstatus/{id}/{value}")
    suspend fun setPaidStatus(@Path("id") id: String, @Path("value") value: Boolean): String
}