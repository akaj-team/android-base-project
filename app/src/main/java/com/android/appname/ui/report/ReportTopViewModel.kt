package com.android.appname.ui.report

import androidx.lifecycle.bindCommonError
import androidx.lifecycle.bindLoading
import com.android.appname.arch.extensions.FlowResult
import com.android.appname.data.network.dtos.GitRepoResponse
import com.android.appname.data.repositories.GitRepository
import com.android.appname.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 * @author at-vinh.huynh
 */
@HiltViewModel
class ReportTopViewModel @Inject constructor(
    private val gitRepository: GitRepository
) : BaseViewModel(), ReportTopVMContract {

    override fun getGithubRepositories(since: Long): Flow<FlowResult<GitRepoResponse?>> =
        gitRepository.getRepositories(since)
            .bindLoading(this)
            .bindCommonError(this)
}
