package com.example.myapplication.Controller
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Path
import android.os.Parcelable


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
}





