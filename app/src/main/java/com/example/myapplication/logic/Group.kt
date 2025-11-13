package com.example.myapplication

import io.ktor.http.*

class Group() {

    fun getGroups() {
        val response: HttpResponse = Network.sendGetRequest("http://localhost:8080/api/groups")
        return response
    }

    fun getGroupsMember(urlInput:String) {
        val response: HttpResponse = Network.sendGetRequest("http://localhost:8080/api/groups/member/$urlInput")
        return response
    }

    fun addGroupMember(urlInput:String) {
        val response: HttpResponse = Network.sendPutRequest("http://localhost:8080/api/addmember/$urlInput")
        return response
    }

    fun addGroupMember(urlInput:String) {
        val response: HttpResponse = Network.sendPutRequest("http://localhost:8080/api/addtransaction/$urlInput")
        return response
    }

    fun createGroup(urlInput:String) {
        val response: HttpResponse = Network.sendPostRequest("http://localhost:8080/api/creategroup/$urlInput")
        return response
    }

}