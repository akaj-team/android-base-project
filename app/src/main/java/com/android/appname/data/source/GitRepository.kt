package com.android.appname.data.source

import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.RestResultWrapper
import com.android.appname.data.source.datasource.GitDataSource
import com.android.appname.di.ApplicationModuleBinds
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GitRepository @Inject constructor(
    @ApplicationModuleBinds.GitRemoteDataSourceQualifier private val gitRemoteDataSource: GitDataSource,
    @ApplicationModuleBinds.GitLocalDataSourceQualifier private val gitLocalDataSource: GitDataSource
) : GitDataSource {
    override suspend fun getRepositorySuspend(since: Long): Flow<RestResultWrapper<List<RepositoryEntity>>> =
        flow {
            emitAll(gitLocalDataSource.getRepositorySuspend(since))
            emitAll(gitRemoteDataSource.getRepositorySuspend(since).onEach {
                if (it is RestResultWrapper.Success) {
                    insertRepositories(*it.value.toTypedArray())
                }
            })
        }

    override suspend fun insertRepositories(vararg repositories: RepositoryEntity) =
        gitLocalDataSource.insertRepositories(*repositories)
}
