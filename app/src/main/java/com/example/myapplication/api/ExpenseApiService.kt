package com.example.myapplication.api

import com.example.myapplication.api.ApiClient.retrofit
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

val expenseApi: ExpenseApiService = retrofit.create(ExpenseApiService::class.java)
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