package com.android.appname.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 *
 * @author at-vinh.huynh
 */
class SwipeLockableViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    private var swipeEnabled = false

    override fun onTouchEvent(event: MotionEvent): Boolean = when (swipeEnabled) {
        true -> super.onTouchEvent(event)
        false -> false
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean = when (swipeEnabled) {
        true -> super.onInterceptTouchEvent(event)
        false -> false
    }

    internal fun setSwipePagingEnabled(swipeEnabled: Boolean) {
        this.swipeEnabled = swipeEnabled
    }
}
