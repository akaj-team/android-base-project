package com.android.appname.ui.dialog.loading

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.android.appname.R
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

/**
 * @author at-hungtruong
 * Copyright © 2019年 Monstar Lab Inc. All rights reserved.
 */
class LoadingDialogFragment : DialogFragment() {
    companion object {
        /**
         * This function is used to create new instance of [LoadingDialogFragment]
         */
        internal fun newInstance() = LoadingDialogFragment()
    }

    private val isShowing = AtomicBoolean(false)

    private val requestShowingCount = AtomicInteger(0)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.run {
            window?.run {
                requestFeature(Window.FEATURE_NO_TITLE)
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                attributes?.gravity = Gravity.CENTER_HORIZONTAL
            }
        }
        isCancelable = false
        return inflater.inflate(R.layout.loading_dialog, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dialog?.window?.run {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                insetsController?.hide(WindowInsets.Type.statusBars())
            } else {
                @Suppress("DEPRECATION")
                setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                )
            }
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        requestShowingCount.incrementAndGet()
        if (!isShowing.get()) {
            isShowing.set(true)
            if (!isAdded) {
                super.show(manager, tag)
            }
        }
    }

    override fun dismissAllowingStateLoss() {
        if (isShowing.get() && requestShowingCount.get() > 0) {
            requestShowingCount.decrementAndGet()
        }
        if (isShowing.get() && requestShowingCount.get() <= 0) {
            isShowing.set(false)
            requestShowingCount.set(0)
            if (activity?.isFinishing == false) {
                super.dismissAllowingStateLoss()
            }
        }
    }
}
