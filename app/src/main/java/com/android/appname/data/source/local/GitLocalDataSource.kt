package com.android.appname.data.source.local

import com.android.appname.data.model.GitRepoResponse
import com.android.appname.data.source.datasource.GitDataSource
import io.reactivex.Single

/**
 * @author at-hungtruong
 */
object GitLocalDataSource : GitDataSource {
    override fun getRepositories(since: Long): Single<List<GitRepoResponse>> = Single.never()
}
