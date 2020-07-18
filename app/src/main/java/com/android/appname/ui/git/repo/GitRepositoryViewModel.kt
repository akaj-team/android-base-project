package com.android.appname.ui.git.repo

import com.android.appname.data.source.GitRepository
import com.android.appname.data.model.GitRepoResponse
import com.android.appname.ui.base.BaseViewModel
import io.reactivex.Single

class GitRepositoryViewModel(private val gitRepository: GitRepository) : BaseViewModel(), GitRepositoryVMContract {
    private val gitRepoList = mutableListOf<GitRepoResponse>()

    override fun gitRepoList(): List<GitRepoResponse> = gitRepoList

    override fun getRepositories(since: Long): Single<MutableList<GitRepoResponse>> =
        gitRepository.getRepositories(since)
            .doOnSuccess {
                gitRepoList.run {
                    clear()
                    addAll(it)
                }
            }
}
