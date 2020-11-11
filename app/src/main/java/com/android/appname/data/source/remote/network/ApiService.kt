package com.android.appname.data.source.remote.network

import com.android.appname.data.model.GitRepoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("repositories")
    fun getRepositories(@Query("since") since: Long): Single<List<GitRepoResponse>>
}
