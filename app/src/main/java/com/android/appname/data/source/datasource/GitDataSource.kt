package com.android.appname.data.source.datasource

import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.GitRepoResponse
import com.android.appname.data.model.RestResultWrapper
import io.reactivex.Single

interface GitDataSource {
    fun getRepositories(since: Long): Single<List<GitRepoResponse>>

    suspend fun getRepositorySuspend(since: Long): RestResultWrapper<List<RepositoryEntity>>

    suspend fun insertRepositories(vararg repositories: RepositoryEntity): List<Long>
}
