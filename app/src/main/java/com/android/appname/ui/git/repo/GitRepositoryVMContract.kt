package com.android.appname.ui.git.repo

import com.android.appname.data.model.GitRepoResponse
import io.reactivex.Single

interface GitRepositoryVMContract {

    fun gitRepoList(): List<GitRepoResponse>

    fun getRepositories(since: Long = 1L): Single<MutableList<GitRepoResponse>>
}
