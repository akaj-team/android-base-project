package com.android.appname.ui.widget

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.android.appname.R
import com.android.appname.arch.extensions.viewBinding
import com.android.appname.data.model.TabItem
import com.android.appname.databinding.CustomMainTabItemBinding

/**
 *
 * @author at-vinh.huynh
 */
class CustomMainTabItem(context: Context) :
    RelativeLayout(context) {
    private val binding by viewBinding(CustomMainTabItemBinding::bind)

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_main_tab_item, this)
    }

    internal fun bindView(tabItem: TabItem) {
        binding.tvTabTitle.text = tabItem.title
        binding.imgTabItem.background = ContextCompat.getDrawable(context, tabItem.bg)
    }

    internal fun setTabSelected(isSelected: Boolean) {
        binding.tvTabTitle.isSelected = isSelected
        binding.imgTabItem.isSelected = isSelected
    }
}
