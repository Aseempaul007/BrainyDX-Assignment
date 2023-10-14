package com.example.brainydxassignment.repository

import com.example.brainydxassignment.data.remote.api.GithubApi
import com.example.brainydxassignment.data.remote.model.ModelClass
import com.example.brainydxassignment.data.remote.model.ModelClassItem
import com.example.brainydxassignment.data.remote.model.Verification
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class UserRepository @Inject constructor(private val githubApi: GithubApi) {

    var users: ArrayList<ModelClassItem>? = null
    suspend fun getUsers(
        owner: String,
        repo: String,
    ): ArrayList<ModelClassItem>{
        users = githubApi.getUserList(owner, repo).body()
        return users!!
    }

    suspend fun getUsersByTime(
        owner: String,
        repo: String,
        since: String,
        until: String,
    ): ArrayList<ModelClassItem>{
        val users = githubApi.getUserListByTime(owner, repo, since, until).body()
        return users!!
    }
}