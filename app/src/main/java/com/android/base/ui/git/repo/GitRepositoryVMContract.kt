package com.android.base.ui.git.repo

import com.android.base.data.source.remote.response.GitRepoResponse
import io.reactivex.Single

interface GitRepositoryVMContract {

    fun gitRepoList(): List<GitRepoResponse>

    fun getRepositories(since: Long = 1L): Single<MutableList<GitRepoResponse>>
}
