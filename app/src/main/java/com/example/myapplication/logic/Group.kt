package com.example.myapplication

import io.ktor.http.*
import com.example.myapplication.Network
import io.ktor.client.statement.HttpResponse

class Group() {

    suspend fun getGroups(): HttpResponse {
        val response: HttpResponse = Network.sendGetRequest("http://localhost:8080/api/groups")
        return response
    }

    suspend fun getGroupsMember(urlInput:String): HttpResponse {
        val response: HttpResponse = Network.sendGetRequest("http://localhost:8080/api/groups/member/$urlInput")
        return response
    }

    suspend fun addGroupMember(urlInput:String): HttpResponse {
        val response: HttpResponse = Network.sendPutRequest("http://localhost:8080/api/addmember/$urlInput")
        return response
    }

    suspend fun addTransaction(urlInput:String): HttpResponse {
        val response: HttpResponse = Network.sendPutRequest("http://localhost:8080/api/addtransaction/$urlInput")
        return response
    }

    suspend fun createGroup(urlInput:String): HttpResponse {
        val response: HttpResponse = Network.sendPostRequest("http://localhost:8080/api/creategroup/$urlInput")
        return response
    }

}