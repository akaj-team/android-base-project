package com.android.appname.ui.main

import android.util.Log
import com.android.appname.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * @author at-hungtruong
 */
class MainViewModel @Inject constructor() : BaseViewModel(), MainVMContract {
    override fun log() {
        Log.d("TAG", "Logdsadsad")
    }
}
