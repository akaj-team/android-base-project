package com.android.appname.ui.git.repo

import androidx.lifecycle.viewModelScope
import com.android.appname.data.model.GitRepoResponse
import com.android.appname.data.source.GitRepository
import com.android.appname.ui.base.BaseViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.launch
import javax.inject.Inject

class GitRepositoryViewModel @Inject constructor(
    private val gitRepository: GitRepository
) : BaseViewModel(), GitRepositoryVMContract {
    private val gitRepoList = mutableListOf<GitRepoResponse>()
    private val loadingChannel = BroadcastChannel<Boolean>(1)

    override fun gitRepoList(): List<GitRepoResponse> = gitRepoList

    override fun getRepositories(since: Long) = gitRepository.getRepositories(since)
        .doOnSuccess {
            gitRepoList.run {
                clear()
                addAll(it)
            }
        }

    override fun getRepositorySuspend(since: Long, onFinished: () -> Unit) {
        viewModelScope.launch {
            gitRepoList.clear()
            loadingChannel.send(true)
            gitRepoList += gitRepository.getRepositorySuspend(since)
            loadingChannel.send(false)
            onFinished()
        }
    }

    override fun getLoadingChannel() = loadingChannel
}
