package com.android.appname.ui.git.repo

import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.RestResultWrapper
import kotlinx.coroutines.flow.StateFlow

interface GitRepositoryVMContract {

    fun gitRepositories(): List<RepositoryEntity>

    fun requestRepositories(onSuccess: () -> Unit, onFailure: (RestResultWrapper.Failure) -> Unit)

    fun getLoadingState(): StateFlow<Boolean>
}
