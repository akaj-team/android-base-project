package com.android.appname.ui.search

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
class SearchContainerFragment : BaseFragment(R.layout.fragment_search_container) {
    companion object {
        fun newInstance() = SearchContainerFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceChildFragment(R.id.frSearchContainer, SearchTopFragment.newInstance())
    }

    override fun getViewModel(): BaseViewModel? = null

}
