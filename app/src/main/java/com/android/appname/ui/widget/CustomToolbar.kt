package com.android.appname.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.appname.R
import com.android.appname.arch.extensions.viewBinding
import com.android.appname.databinding.CustomToolbarBinding

/**
 *
 * @author at-vinh.huynh
 */
class CustomToolbar(context: Context, attrs: AttributeSet) :
    RelativeLayout(context, attrs) {

    private val binding by viewBinding(CustomToolbarBinding::bind)

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this)
    }

    internal fun setTitle(@StringRes titleRes: Int) {
        binding.tvToolbarTitle.text = context?.getString(titleRes)
    }

    internal fun setVisibleLeftIcon(visibility: Int) {
        binding.imgToolbarLeft.visibility = visibility
    }

    internal fun setLeftIconResource(@DrawableRes resource: Int) {
        binding.imgToolbarLeft.setImageResource(resource)
    }

    internal fun setVisibleRightIcon(visibility: Int) {
        binding.imgToolbarRight.visibility = visibility
    }

    internal fun setRightIconResource(@DrawableRes resource: Int) {
        binding.imgToolbarRight.setImageResource(resource)
    }
}
