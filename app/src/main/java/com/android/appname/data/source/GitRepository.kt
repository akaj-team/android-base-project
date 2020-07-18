package com.android.appname.data.source

import com.android.appname.data.source.datasource.GitDataSource
import com.android.appname.data.source.remote.GitRemoteDataSource
import com.android.appname.data.model.GitRepoResponse
import io.reactivex.Single

class GitRepository : GitDataSource {
    private val gitRDS = GitRemoteDataSource()

    override fun getRepositories(since: Long): Single<MutableList<GitRepoResponse>> {
        return gitRDS.getRepositories(since)
    }
}
