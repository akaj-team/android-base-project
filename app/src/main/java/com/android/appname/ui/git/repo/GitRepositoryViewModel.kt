package com.android.appname.ui.git.repo

import androidx.lifecycle.viewModelScope
import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.RestResultWrapper
import com.android.appname.data.source.GitRepository
import com.android.appname.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class GitRepositoryViewModel @Inject constructor(
    private val gitRepository: GitRepository
) : BaseViewModel(), GitRepositoryVMContract {
    private val gitRepoList = mutableListOf<RepositoryEntity>()

    override fun gitRepoList() = gitRepoList

    override fun getRepositories(since: Long) = gitRepository.getRepositories(since)
        .doOnSuccess {
            gitRepoList.run {
                clear()
//                addAll(it)
            }
        }

    override fun getRepositorySuspend(since: Long, onFinished: () -> Unit) {
        viewModelScope.launch {
            gitRepoList.clear()
            loadingLiveData.postValue(true)
            val result = gitRepository.getRepositorySuspend(since)
            if (result is RestResultWrapper.Success) {
                gitRepoList += result.value
            } else if (result is RestResultWrapper.Failure) {
                errorResultWrapperLiveData.postValue(result)
            }
            loadingLiveData.postValue(false)
            onFinished()
        }
    }

    override fun getLoadingProgress() = loadingLiveData

    override fun getResultFailureLiveData() = errorResultWrapperLiveData
}
