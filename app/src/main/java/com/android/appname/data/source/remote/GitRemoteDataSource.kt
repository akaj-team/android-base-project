package com.android.appname.data.source.remote

import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.source.datasource.GitDataSource
import com.android.appname.data.source.remote.network.ApiService
import com.android.appname.data.source.remote.network.SafeApiCallService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GitRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val safeApiCall: SafeApiCallService
) : GitDataSource {
    override suspend fun getRepositorySuspend(since: Long) =
        safeApiCall.call {
            apiService.getRepositorySuspend(since).map { response ->
                response.toEntity()
            }
        }.toFlow(Dispatchers.IO)

    override suspend fun insertRepositories(vararg repositories: RepositoryEntity) =
        emptyList<Long>()
}
