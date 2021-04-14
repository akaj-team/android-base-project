package com.android.appname.ui

import com.android.appname.data.model.TabItem

/**
 *
 * @author at-vinh.huynh
 */
interface MainVMContract {
    fun getTabItems(): MutableList<TabItem>
}
