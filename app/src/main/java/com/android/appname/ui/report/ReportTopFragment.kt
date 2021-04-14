package com.android.appname.ui.report

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.android.appname.R
import com.android.appname.arch.extensions.onError
import com.android.appname.arch.extensions.onSuccess
import com.android.appname.arch.extensions.viewBinding
import com.android.appname.databinding.FragmentReportTopBinding
import com.android.appname.ui.base.BaseFragment
import com.android.appname.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn

/**
 *
 * @author at-vinh.huynh
 */
@AndroidEntryPoint
class ReportTopFragment : BaseFragment(R.layout.fragment_report_top) {

    private val binding by viewBinding(FragmentReportTopBinding::bind)
    private val viewModel by viewModels<ReportTopViewModel>()

    companion object {
        fun newInstance() = ReportTopFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGithubRepositories(12313123123)
            .onSuccess {
                // Success
                binding.tvRepoName.text = it?.fullName
            }.onError(normalAction = {
                // Error
            }).launchIn(lifecycleScope)
    }

    override fun getViewModel(): BaseViewModel? = viewModel
}
