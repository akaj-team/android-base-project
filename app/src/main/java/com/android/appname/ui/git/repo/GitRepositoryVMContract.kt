package com.android.appname.ui.git.repo

import com.android.appname.data.model.GitRepoResponse
import io.reactivex.Single
import kotlinx.coroutines.channels.BroadcastChannel

interface GitRepositoryVMContract {

    fun gitRepoList(): List<GitRepoResponse>

    fun getRepositories(since: Long = 1L): Single<List<GitRepoResponse>>

    fun getRepositorySuspend(since: Long = 1L, onFinished: () -> Unit)

    fun getLoadingChannel(): BroadcastChannel<Boolean>
}
