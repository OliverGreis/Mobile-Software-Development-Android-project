package com.example.myapplication.Controller

import com.example.myapplication.Controller.ApiClient.retrofit
import com.example.myapplication.model.Transaction
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


val transactionApi: TransactionApiService = retrofit.create(TransactionApiService::class.java)
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