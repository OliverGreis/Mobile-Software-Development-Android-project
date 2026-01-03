package com.example.myapplication.Controller
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path
import android.os.Parcelable
import com.example.myapplication.Controller.ApiClient.retrofit
import retrofit2.http.PUT
import com.example.myapplication.model.User



data class Group(
    val id: Int,
    val name: String,
    val memberIDs: List<String>,
    val transactionIDs: List<Int>,
    val creationDate: String
)

data class Users(
    val username: String,
    val email: String,
    val password: String,
    val number: String,
    val carsRented: List<String>
)


val groupApi: GroupApiService = retrofit.create(GroupApiService::class.java)
val userApi: UserApiService = retrofit.create(UserApiService::class.java)


interface GroupApiService {
    @GET("api/groups")
    suspend fun getGroups(): List<Group>

    @POST("api/creategroup/{name}")
    suspend fun createGroup(@Path("name") name: String): Group
}

interface UserApiService {
    @GET("api/users")
    suspend fun getUsers(): List<User>

    @POST("api/users/create/{username}/{email}/{password}/{number}")
    suspend fun createUser(
        @Path("username") username: String,
        @Path("email") email: String,
        @Path("password") password: String,
        @Path("number") number: String
    ): String

    @POST("api/login/{email}/{password}")
    suspend fun loginUser(
        @Path("email") email: String,
        @Path("password") password: String
    ): String

    @PUT("api/reset/{email}/{newPassword}")
    suspend fun resetPassword(
        @Path("email") email: String,
        @Path("newPassword") newPassword: String
    ): String
}




