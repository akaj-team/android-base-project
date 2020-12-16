package com.android.appname.ui.git.repo

import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.RestResultWrapper
import com.android.appname.data.source.GitRepository
import com.android.appname.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GitRepositoryViewModel @Inject constructor(
    private val gitRepository: GitRepository
) : BaseViewModel(), GitRepositoryVMContract {
    private var requestRepositoriesSince = 1L

    private val repositories = mutableListOf<RepositoryEntity>()
    private val loadingState = MutableStateFlow(false)

    override fun gitRepositories() = repositories

    override suspend fun requestRepositories() =
        gitRepository.getRepositorySuspend(requestRepositoriesSince).onStart {
            repositories.clear()
            loadingState.emit(true)
        }.onCompletion {
            loadingState.emit(false)
        }.onEach {
            repositories.clear()
            (it as? RestResultWrapper.Success)?.run {
                repositories += value
            }
        }.flowOn(Dispatchers.Default)

    override fun getLoadingState() = loadingState
}
