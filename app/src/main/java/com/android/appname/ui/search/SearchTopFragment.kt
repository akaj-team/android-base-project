package com.android.appname.ui.search

import com.android.appname.R
import com.android.appname.arch.extensions.viewBinding
import com.android.appname.databinding.FragmentSearchTopBinding
import com.android.appname.ui.base.BaseFragment
import com.android.appname.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 *
 * @author at-vinh.huynh
 */
@AndroidEntryPoint
class SearchTopFragment : BaseFragment(R.layout.fragment_search_top) {
    private val binding by viewBinding(FragmentSearchTopBinding::bind)

    companion object {
        fun newInstance() = SearchTopFragment()
    }

    override fun getViewModel(): BaseViewModel? = null

}
