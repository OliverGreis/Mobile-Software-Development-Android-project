package com.example.myapplication.api

import com.example.myapplication.api.ApiClient.retrofit
import com.example.myapplication.model.Group
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

// Temp solution
val groupApi: GroupApiService = retrofit.create(GroupApiService::class.java)
interface GroupApiService {
    @GET("api/groups")
    suspend fun getGroups(): List<Group>

    @POST("api/creategroup/{name}")
    suspend fun createGroup(@Path("name") name: String): String

    @GET("api/groups/member/{id}")
    suspend fun getGroupsForMember(@Path("id") id: String): List<Group>

    @PUT("api/addmember/{username}/{groupId}")
    suspend fun addMember(@Path("groupId") id: Int, @Path("username") username: String): String

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

    //returns a string that is the url path to the image hosted on the spring backend
    @GET("api/group/getimage/{id}")
    suspend fun getGroupImage(@Path("id") id: Int): String

    //image is a single number from 1 to 4 that changes the group image
    @PUT("api/group/setimage/{id}/{image}")
    suspend fun setGroupImage(@Path("id") id: Int, @Path("image") image: Int): String
}