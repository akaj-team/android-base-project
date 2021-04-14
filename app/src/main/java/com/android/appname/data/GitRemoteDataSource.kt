package com.android.appname.data

import com.android.appname.arch.extensions.apiCall
import com.android.appname.data.network.Api
import com.android.appname.data.network.dtos.GitRepoResponse
import javax.inject.Inject

/**
 *
 * @author at-vinh.huynh
 */
class GitRemoteDataSource @Inject constructor(
    private val api: Api,
) {
    suspend fun getRepositories(since: Long): GitRepoResponse = apiCall {
        api.getRepositories(since)
    }
}
