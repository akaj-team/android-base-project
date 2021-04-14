package com.android.appname.ui

import androidx.lifecycle.ViewModel
import com.android.appname.data.model.TabItem

/**
 *
 * @author at-vinh.huynh
 */
class MainViewModel : ViewModel(), MainVMContract {
    override fun getTabItems(): MutableList<TabItem> {
        return mutableListOf<TabItem>().apply {
            add(TabItem.REPORT)
            add(TabItem.SEARCH)
            add(TabItem.VIDEO)
            add(TabItem.FOUNDATION)
            add(TabItem.SETTING)
        }
    }
}
