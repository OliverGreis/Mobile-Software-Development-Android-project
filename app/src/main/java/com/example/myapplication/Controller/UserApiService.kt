package com.example.myapplication.Controller

import com.example.myapplication.Controller.ApiClient.retrofit
import com.example.myapplication.model.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


val userApi: UserApiService = retrofit.create(UserApiService::class.java)
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

    //returns a string that is the url path to the image hosted on the spring backend
    @GET("api/user/getimage/{username}")
    suspend fun getGroupImage(@Path("username") username: String): String

    //image is a single number from 1 to 6 that changes the profile image
    @PUT("api/user/setimage/{username}/{image}")
    suspend fun setGroupImage(@Path("username") username: String, @Path("image") image: Int): String

    @GET("api/user/getphonenumber/{username}")
    suspend fun getPhoneNumber(@Path("username") username: String): Int

    @PUT("api/user/setimage/{username}/{phonenumber}")
    suspend fun setPhoneNumber(@Path("username") username: String, @Path("phonenumber") phonenumber: Int): String
}
