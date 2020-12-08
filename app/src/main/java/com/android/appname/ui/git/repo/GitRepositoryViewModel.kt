package com.android.appname.ui.git.repo

import androidx.lifecycle.viewModelScope
import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.model.RestResultWrapper
import com.android.appname.data.source.GitRepository
import com.android.appname.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GitRepositoryViewModel @Inject constructor(
    private val gitRepository: GitRepository
) : BaseViewModel(), GitRepositoryVMContract {
    private var requestRepositoriesSince = 1L

    private val repositories = mutableListOf<RepositoryEntity>()
    private val loadingState = MutableStateFlow(false)

    override fun gitRepositories() = repositories

    override fun requestRepositories(
        onSuccess: () -> Unit,
        onFailure: (RestResultWrapper.Failure) -> Unit
    ) {
        viewModelScope.launch {
            repositories.clear()
            loadingState.emit(true)
            gitRepository.getRepositorySuspend(requestRepositoriesSince).onResultResponsed({
                repositories += it.value
                onSuccess()
            }, {
                onFailure(it)
            })
            loadingState.emit(false)
        }
    }

    override fun getLoadingState() = loadingState
}
