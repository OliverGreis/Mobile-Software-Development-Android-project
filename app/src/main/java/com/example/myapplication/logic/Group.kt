package com.example.myapplication

import io.ktor.http.*
import com.example.myapplication.Network
import io.ktor.client.statement.HttpResponse

class Group() {

    suspend fun getGroups(): HttpResponse {
        val response: HttpResponse = Network.sendGetRequest("http://localhost:8080/api/groups")
        return response
    }

    //"/api/groups/member/{id}"
    suspend fun getGroupsMember(urlInput:String): HttpResponse {
        val response: HttpResponse = Network.sendGetRequest("http://localhost:8080/api/groups/member/$urlInput")
        return response
    }

    //"/api/group/{id}"
    suspend fun GetGroup(urlInput:String): HttpResponse {
        val response: HttpResponse = Network.sendGetRequest("http://localhost:8080/api/group/$urlInput")
        return response
    }

    //"/api/addmember/{id}/{groupID}"
    suspend fun addGroupMember(urlInput:String): HttpResponse {
        val response: HttpResponse = Network.sendPutRequest("http://localhost:8080/api/addmember/$urlInput")
        return response
    }

    //"/api/addtransaction/{id}/{groupID}"
    suspend fun addTransaction(urlInput:String): HttpResponse {
        val response: HttpResponse = Network.sendPutRequest("http://localhost:8080/api/addtransaction/$urlInput")
        return response
    }

    //"api/creategroup/{name}"
    suspend fun createGroup(urlInput:String): HttpResponse {
        val response: HttpResponse = Network.sendPostRequest("http://localhost:8080/api/creategroup/$urlInput")
        return response
    }

}