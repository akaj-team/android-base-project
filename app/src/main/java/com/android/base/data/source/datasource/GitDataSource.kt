package com.android.base.data.source.datasource

import com.android.base.data.source.remote.response.GitRepoResponse
import io.reactivex.Single

interface GitDataSource {
    fun getRepositories(since: Long): Single<MutableList<GitRepoResponse>>
}
