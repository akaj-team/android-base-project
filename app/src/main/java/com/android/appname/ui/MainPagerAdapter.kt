package com.android.appname.ui

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.android.appname.data.model.TabItem

/**
 *
 * @author at-vinh.huynh
 */
class MainPagerAdapter(fragmentManager: FragmentManager, private val tabItems: List<TabItem>) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = tabItems[position].owner

    override fun getCount() = tabItems.size

    override fun getItemPosition(`object`: Any) = PagerAdapter.POSITION_NONE
}
