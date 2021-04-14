package com.android.appname.ui

import androidx.activity.viewModels
import com.android.appname.R
import com.android.appname.arch.extensions.viewBinding
import com.android.appname.data.model.TabItem
import com.android.appname.databinding.ActivityMainBinding
import com.android.appname.ui.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.android.appname.ui.widget.CustomMainTabItem
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * @author at-vinh.huynh
 */
@AndroidEntryPoint
class MainActivity : BaseActivity(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun afterViewCreated() {
        initListener()
        initMainTab()
    }

    private fun initMainTab() {
        val mainPagerAdapter = MainPagerAdapter(supportFragmentManager, viewModel.getTabItems())
        binding.viewPager.offscreenPageLimit = viewModel.getTabItems().size
        binding.viewPager.adapter = mainPagerAdapter
        for (i in 0 until viewModel.getTabItems().size) {
            addTabItem(viewModel.getTabItems()[i], i)
        }
    }

    private fun initListener() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                (tab?.tag as? Int)?.also {
                    binding.viewPager.setCurrentItem(it, false)
                    updateTabSelected(it)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun addTabItem(tabItem: TabItem, pos: Int) {
        val tab = binding.tabLayout.newTab()
        tab.customView = CustomMainTabItem(this).apply {
            bindView(tabItem)
        }
        tab.tag = pos
        binding.tabLayout.addTab(tab)
    }

    private fun updateTabSelected(pos: Int) {
        for (i in 0 until viewModel.getTabItems().size) {
            val customTabView = binding.tabLayout.getTabAt(i)?.customView
            (customTabView as? CustomMainTabItem)?.run {
                setTabSelected(i == pos)
            }
        }
    }
}
