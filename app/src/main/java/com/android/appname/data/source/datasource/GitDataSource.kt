package com.android.appname.data.source.datasource

import com.android.appname.data.model.GitRepoResponse
import io.reactivex.Single

interface GitDataSource {
    fun getRepositories(since: Long): Single<List<GitRepoResponse>>

    suspend fun getRepositorySuspend(since: Long): List<GitRepoResponse>
}
