package com.android.appname.ui.git.repo

import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.RestResultWrapper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.StateFlow

interface GitRepositoryVMContract {

    fun gitRepositories(): List<RepositoryEntity>

    suspend fun requestRepositoriesAsync(): Deferred<RestResultWrapper<List<RepositoryEntity>>>

    fun getLoadingState(): StateFlow<Boolean>
}
