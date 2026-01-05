package com.example.myapplication.repository

import com.example.myapplication.api.GroupApiService
import com.example.myapplication.model.Group

class GroupRepository(private val groupApiService: GroupApiService) {
    suspend fun createGroup(groupName: String): String {
        return groupApiService.createGroup(groupName)
    }

    suspend fun getGroups(): List<Group> {
        return groupApiService.getGroups()
    }

    suspend fun addMember(groupId: Int, username: String): String {
        return groupApiService.addMember(groupId, username)
    }

    suspend fun getGroupImage(groupId: Int): String {
        return groupApiService.getGroupImage(groupId)
    }






}