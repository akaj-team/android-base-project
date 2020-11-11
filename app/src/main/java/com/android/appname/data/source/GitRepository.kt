package com.android.appname.data.source

import com.android.appname.data.source.datasource.GitDataSource
import com.android.appname.di.ApplicationModule
import javax.inject.Inject

class GitRepository @Inject constructor(
    @ApplicationModule.GitRemoteDataSource private val gitRemoteDataSource: GitDataSource,
    @ApplicationModule.GitLocalDataSource private val gitLocalDataSource: GitDataSource
) : GitDataSource {

    override fun getRepositories(since: Long) = gitRemoteDataSource.getRepositories(since)

    override suspend fun getRepositorySuspend(since: Long) =
        gitRemoteDataSource.getRepositorySuspend(since)
}
