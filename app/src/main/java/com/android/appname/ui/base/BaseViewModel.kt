package com.android.appname.ui.base

import androidx.lifecycle.ViewModel
import com.android.appname.arch.extensions.LoadingAware
import com.android.appname.arch.extensions.ViewErrorAware

/**
 *
 * @author at-vinh.huynh
 */
open class BaseViewModel : ViewModel(), ViewErrorAware, LoadingAware
