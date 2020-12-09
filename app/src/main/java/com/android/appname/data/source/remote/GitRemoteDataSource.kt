package com.android.appname.data.source.remote

import com.android.appname.data.model.GitRepoResponse
import com.android.appname.data.source.datasource.GitDataSource
import com.android.appname.data.source.remote.network.ApiClient
import com.android.appname.data.source.remote.network.ApiService
import io.reactivex.Single

class GitRemoteDataSource(private val apiService: ApiService = ApiClient.getInstance().service) :
    GitDataSource {

    override fun getRepositories(since: Long): Single<MutableList<GitRepoResponse>> {
        return apiService.getRepositories(since)
    }
}
