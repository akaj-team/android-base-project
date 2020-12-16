package com.android.appname.data.source.remote.network

import com.android.appname.data.model.GitRepoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("repositories")
    suspend fun getRepositorySuspend(@Query("since") since: Long): List<GitRepoResponse>
}
