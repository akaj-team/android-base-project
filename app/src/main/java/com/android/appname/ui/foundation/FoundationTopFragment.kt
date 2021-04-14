package com.android.appname.ui.foundation

import com.android.appname.R
import com.android.appname.arch.extensions.viewBinding
import com.android.appname.databinding.FragmentSettingTopBinding
import com.android.appname.ui.base.BaseFragment
import com.android.appname.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author at-vinh.huynh
 */
@AndroidEntryPoint
class FoundationTopFragment : BaseFragment(R.layout.fragment_setting_top) {
    private val binding by viewBinding(FragmentSettingTopBinding::bind)

    companion object {
        fun newInstance() = FoundationTopFragment()
    }

    override fun getViewModel(): BaseViewModel? = null
}
