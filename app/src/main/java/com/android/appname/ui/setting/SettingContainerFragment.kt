package com.android.appname.ui.setting

import com.android.appname.R
import com.android.appname.ui.base.BaseFragment
import com.android.appname.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author at-vinh.huynh
 */
@AndroidEntryPoint
class SettingContainerFragment : BaseFragment(R.layout.fragment_more_container) {
    companion object {
        fun newInstance() = SettingContainerFragment()
    }

    override fun getViewModel(): BaseViewModel? = null

}
