package com.android.appname.data.source.datasource

import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.RestResultWrapper
import kotlinx.coroutines.flow.Flow

interface GitDataSource {
    suspend fun getRepositorySuspend(since: Long): Flow<RestResultWrapper<List<RepositoryEntity>>>

    suspend fun insertRepositories(vararg repositories: RepositoryEntity): List<Long>
}
