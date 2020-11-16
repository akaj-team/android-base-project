package com.android.appname.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.appname.data.model.RestResultWrapper

open class BaseViewModel : ViewModel() {
    protected val loadingLiveData = MutableLiveData(false)
    protected val errorResultWrapperLiveData = MutableLiveData<RestResultWrapper.Failure>()
}
