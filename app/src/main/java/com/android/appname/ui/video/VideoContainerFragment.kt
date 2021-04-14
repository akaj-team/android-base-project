package com.android.appname.ui.video

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
class VideoContainerFragment : BaseFragment(R.layout.fragment_video_container) {
    companion object {
        fun newInstance() = VideoContainerFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceChildFragment(R.id.frVideoContainer, VideoTopFragment.newInstance())
    }

    override fun getViewModel(): BaseViewModel? = null

}
