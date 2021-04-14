package com.android.appname.data.network

import com.android.appname.data.network.dtos.GitRepoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("repositories")
    suspend fun getRepositories(@Query("since") since: Long): Response<GitRepoResponse>
}
