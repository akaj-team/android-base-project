package com.android.appname.ui.report

import com.android.appname.arch.extensions.FlowResult
import com.android.appname.data.network.dtos.GitRepoResponse
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author at-vinh.huynh
 */
interface ReportTopVMContract {
    fun getGithubRepositories(since: Long): Flow<FlowResult<GitRepoResponse?>>
}
