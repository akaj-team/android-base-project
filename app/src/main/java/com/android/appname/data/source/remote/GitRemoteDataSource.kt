package com.android.appname.data.source.remote

import com.android.appname.data.source.datasource.GitDataSource
import com.android.appname.data.source.remote.network.ApiClient
import com.android.appname.data.source.remote.network.ApiService
import com.android.appname.data.model.GitRepoResponse
import io.reactivex.Single

class GitRemoteDataSource(private val apiService: ApiService) : GitDataSource {
    constructor() : this(ApiClient.getInstance(null).service)

    override fun getRepositories(since: Long): Single<MutableList<GitRepoResponse>> {
        return apiService.getRepositories(since)
    }
}
