package com.android.appname.data.model

import androidx.annotation.DrawableRes
import com.android.appname.R
import com.android.appname.ui.base.BaseFragment
import com.android.appname.ui.foundation.FoundationContainerFragment
import com.android.appname.ui.report.ReportContainerFragment
import com.android.appname.ui.search.SearchContainerFragment
import com.android.appname.ui.setting.SettingContainerFragment
import com.android.appname.ui.video.VideoContainerFragment

/**
 *
 * @author at-vinh.huynh
 */
enum class TabItem(val title: String, val owner: BaseFragment, @DrawableRes val bg: Int) {
    REPORT("Báo cáo", ReportContainerFragment.newInstance(), R.drawable.selector_tab_item_report),
    SEARCH("Tìm kiếm", SearchContainerFragment.newInstance(), R.drawable.selector_tab_item_search),
    VIDEO("Video", VideoContainerFragment.newInstance(), R.drawable.selector_tab_item_video),
    FOUNDATION(
        "Foundation",
        FoundationContainerFragment.newInstance(),
        R.drawable.selector_tab_item_foundation
    ),
    SETTING("Cài đặt", SettingContainerFragment.newInstance(), R.drawable.selector_tab_item_setting)
}
