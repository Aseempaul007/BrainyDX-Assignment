package com.example.brainydxassignment.data.remote.api

import com.example.brainydxassignment.data.remote.model.ModelClassItem
import com.example.brainydxassignment.data.remote.model.Verification
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @Headers(
        "Accept: application/vnd.github+json"
    )

    @GET("/repos/{owner}/{repo}/commits")
    suspend fun getUserList(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Response<ArrayList<ModelClassItem>>

    @GET("/repos/{owner}/{repo}/commits")
    suspend fun getUserListByTime(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("since") since: String,
        @Query("until") until: String,
    ): Response<ArrayList<ModelClassItem>>

}