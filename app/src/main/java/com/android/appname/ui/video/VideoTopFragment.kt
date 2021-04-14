package com.android.appname.ui.video

import com.android.appname.R
import com.android.appname.arch.extensions.viewBinding
import com.android.appname.databinding.FragmentVideoTopBinding
import com.android.appname.ui.base.BaseFragment
import com.android.appname.ui.base.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author at-vinh.huynh
 */
@AndroidEntryPoint
class VideoTopFragment : BaseFragment(R.layout.fragment_video_top) {
    private val binding by viewBinding(FragmentVideoTopBinding::bind)

    companion object {
        fun newInstance() = VideoTopFragment()
    }

    override fun getViewModel(): BaseViewModel? = null

}
