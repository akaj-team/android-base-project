package com.android.appname.data.source.remote

import com.android.appname.data.source.datasource.GitDataSource
import com.android.appname.data.source.remote.network.ApiClient
import com.android.appname.data.source.remote.network.ApiService

object GitRemoteDataSource : GitDataSource {
    private val apiService: ApiService = ApiClient.getInstance(null).service

    override fun getRepositories(since: Long) = apiService.getRepositories(since)
}
