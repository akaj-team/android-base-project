package com.android.appname.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.loadingFlow
import androidx.lifecycle.viewErrorFlow
import com.android.appname.arch.extensions.collectFlow

/**
 *
 * @author at-vinh.huynh
 */
abstract class BaseFragment(@LayoutRes val layoutId: Int) : Fragment(layoutId) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()?.run {
            collectFlow(viewErrorFlow) {
                (activity as? BaseActivity)?.handleCommonError(it)
            }

            collectFlow(loadingFlow) {
                (activity as? BaseActivity)?.handleProgressDialogStatus(it)
            }
        }
    }

    abstract fun getViewModel(): BaseViewModel?
}
