package com.example.myapplication.Controller
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path
import android.os.Parcelable
import retrofit2.http.PUT


data class Group(
    val id: Int,
    val name: String,
    val memberIDs: List<String>,
    val transactionIDs: List<Int>,
    val creationDate: String
)

private const val BASE_URL =
    "http://10.0.2.2:8080/"


private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

val groupApi: GroupApiService = retrofit.create(GroupApiService::class.java)


interface GroupApiService {
    @GET("api/groups")
    suspend fun getGroups(): List<Group>

    @POST("api/creategroup/{name}")
    suspend fun createGroup(@Path("name") name: String): Group

    @GET("api/groups/member/{id}")
    suspend fun getGroupsForMember(@Path("id") id: String): List<Group>

    @PUT("api/addmember/{id}/{groupID}")
    suspend fun addMember(@Path("id") id: String, @Path("groupID") groupID: Integer): String

    @PUT("api/addtransaction/{id}/{groupID}")
    suspend fun addTransaction(@Path("id") id: String, @Path("groupID") groupID: Integer): String

    @GET("api/group/{id}")
    suspend fun getGroup(@Path("id") id: String): String

    @PUT("api/removemember/{id}/{groupID}")
    suspend fun removeMember(@Path("id") id: String, @Path("groupID") groupID: Integer): String

    @PUT("api/removetransaction/{id}/{groupID}")
    suspend fun removeTransaction(@Path("id") id: String, @Path("groupID") groupID: Integer): String

}





