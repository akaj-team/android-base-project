package com.android.appname.data.repositories

import com.android.appname.arch.data.Repository
import com.android.appname.arch.extensions.FlowResult
import com.android.appname.arch.extensions.safeFlow
import com.android.appname.data.GitRemoteDataSource
import com.android.appname.data.network.dtos.GitRepoResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitRepository @Inject constructor(
    private val gitRemoteDataSource: GitRemoteDataSource
) : Repository() {

    fun getRepositories(since: Long): Flow<FlowResult<GitRepoResponse>> = safeFlow {
        gitRemoteDataSource.getRepositories(since)
    }
}
