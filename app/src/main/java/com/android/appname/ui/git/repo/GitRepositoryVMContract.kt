package com.android.appname.ui.git.repo

import androidx.lifecycle.LiveData
import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.GitRepoResponse
import com.android.appname.data.model.RestResultWrapper
import io.reactivex.Single

interface GitRepositoryVMContract {

    fun gitRepoList(): List<RepositoryEntity>

    fun getRepositories(since: Long = 1L): Single<List<GitRepoResponse>>

    fun getRepositorySuspend(since: Long = 1L, onFinished: () -> Unit)

    fun getLoadingProgress(): LiveData<Boolean>

    fun getResultFailureLiveData(): LiveData<RestResultWrapper.Failure>
}
