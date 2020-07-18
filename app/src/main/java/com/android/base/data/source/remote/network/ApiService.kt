package com.android.base.data.source.remote.network

import com.android.base.data.source.remote.response.GitRepoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("repositories")
    fun getRepositories(@Query("since") since: Long): Single<MutableList<GitRepoResponse>>
}
