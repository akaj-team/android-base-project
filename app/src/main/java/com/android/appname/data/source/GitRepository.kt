package com.android.appname.data.source

import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.RestResultWrapper
import com.android.appname.data.model.toResultSuccess
import com.android.appname.data.source.datasource.GitDataSource
import com.android.appname.di.ApplicationModuleBinds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

class GitRepository @Inject constructor(
    @ApplicationModuleBinds.GitRemoteDataSourceQualifier private val gitRemoteDataSource: GitDataSource,
    @ApplicationModuleBinds.GitLocalDataSourceQualifier private val gitLocalDataSource: GitDataSource
) : GitDataSource {

    override fun getRepositories(since: Long) = gitRemoteDataSource.getRepositories(since)

    override suspend fun getRepositorySuspend(since: Long): RestResultWrapper<List<RepositoryEntity>> {
        val remoteResultWrapper = gitRemoteDataSource.getRepositorySuspend(since)
        val localResultWrapper = gitLocalDataSource.getRepositorySuspend(since)
        if (remoteResultWrapper !is RestResultWrapper.Success) {
            return remoteResultWrapper
        }
        if (localResultWrapper !is RestResultWrapper.Success) {
            return localResultWrapper
        }
        val localRepository = flowOf(localResultWrapper)
        val remoteRepository = flowOf(remoteResultWrapper)
        return localRepository.zip(remoteRepository) { local, remote ->
            val result = mutableMapOf<Long, RepositoryEntity>()
            result.putAll(local.value.associateBy { it.id })
            result.putAll(remote.value.associateBy { it.id })
            result.map { it.value }.also {
                val ids = insertRepositories(*it.toTypedArray())
                it.forEachIndexed { index, entity ->
                    entity.id = ids[index]
                }
            }
        }.flowOn(Dispatchers.Default)
            .single()
            .toResultSuccess()
    }

    override suspend fun insertRepositories(vararg repositories: RepositoryEntity) =
        gitLocalDataSource.insertRepositories(*repositories)
}
