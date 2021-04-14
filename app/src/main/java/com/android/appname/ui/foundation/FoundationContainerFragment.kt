package com.android.appname.ui.foundation

import android.os.Bundle
import android.view.View
import com.android.appname.R
import com.android.appname.arch.extensions.replaceChildFragment
import com.android.appname.ui.base.BaseFragment
import com.android.appname.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author at-vinh.huynh
 */
@AndroidEntryPoint
class FoundationContainerFragment : BaseFragment(R.layout.fragment_more_container) {
    companion object {
        fun newInstance() = FoundationContainerFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceChildFragment(R.id.frMoreContainer, FoundationTopFragment.newInstance())
    }

    override fun getViewModel(): BaseViewModel? = null
}
