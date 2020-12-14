package com.android.appname.ui.git.repo

import androidx.lifecycle.viewModelScope
import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.RestResultWrapper
import com.android.appname.data.source.GitRepository
import com.android.appname.ui.base.BaseViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class GitRepositoryViewModel @Inject constructor(
    private val gitRepository: GitRepository
) : BaseViewModel(), GitRepositoryVMContract {
    private var requestRepositoriesSince = 1L

    private val repositories = mutableListOf<RepositoryEntity>()
    private val loadingState = MutableStateFlow(false)

    override fun gitRepositories() = repositories

    override suspend fun requestRepositoriesAsync(): Deferred<RestResultWrapper<List<RepositoryEntity>>> {
        return viewModelScope.async {
            repositories.clear()
            loadingState.emit(true)
            val result =
                gitRepository.getRepositorySuspend(requestRepositoriesSince).doOnResultResponded({
                    repositories += it.value
                })
            loadingState.emit(false)
            result
        }
    }

    override fun getLoadingState() = loadingState
}
