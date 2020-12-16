package com.android.appname.data.source.local

import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.RestResultWrapper
import com.android.appname.data.source.datasource.GitDataSource
import com.android.appname.data.source.local.daos.RepositoryDao
import javax.inject.Inject

/**
 * @author at-hungtruong
 */
class GitLocalDataSource @Inject constructor(private val repositoryDao: RepositoryDao) :
    GitDataSource {
    override suspend fun getRepositorySuspend(since: Long) =
        RestResultWrapper.Success(repositoryDao.getRepositories()).toFlow()

    override suspend fun insertRepositories(vararg repositories: RepositoryEntity) =
        repositoryDao.insertAll(*repositories)
}
