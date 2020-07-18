package com.android.base.data.source

import com.android.base.data.source.datasource.GitDataSource
import com.android.base.data.source.remote.GitRemoteDataSource
import com.android.base.data.source.remote.response.GitRepoResponse
import io.reactivex.Single

class GitRepository : GitDataSource {
    private val gitRDS = GitRemoteDataSource()

    override fun getRepositories(since: Long): Single<MutableList<GitRepoResponse>> {
        return gitRDS.getRepositories(since)
    }
}
