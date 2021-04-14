package com.android.appname.ui.report

import android.os.Bundle
import android.view.View
import com.android.appname.R
import com.android.appname.arch.extensions.replaceChildFragment
import com.android.appname.ui.base.BaseFragment
import com.android.appname.ui.base.BaseViewModel

/**
 *
 * @author at-vinh.huynh
 */
class ReportContainerFragment : BaseFragment(R.layout.fragment_report_container) {
    companion object {
        fun newInstance() = ReportContainerFragment()
    }

    override fun getViewModel(): BaseViewModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceChildFragment(R.id.frReportContainer, ReportTopFragment.newInstance())
    }
}
