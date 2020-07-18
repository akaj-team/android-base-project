package com.android.base.ui.git.repo

import com.android.base.data.source.GitRepository
import com.android.base.data.source.remote.response.GitRepoResponse
import io.reactivex.Single

class GitRepositoryViewModel(private val gitRepository: GitRepository) : GitRepositoryVMContract {
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
