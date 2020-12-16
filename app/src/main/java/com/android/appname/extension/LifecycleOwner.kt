package com.android.appname.extension

import androidx.lifecycle.LifecycleOwner
import com.android.appname.data.model.RestResultWrapper
import com.android.appname.ui.base.BaseActivity
import com.android.appname.ui.base.BaseFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * @author at-hungtruong
 */
suspend fun <T> LifecycleOwner.requestApi(
    flow: Flow<RestResultWrapper<T>>,
    onEachValue: (T) -> Unit,
    onFailure: ((RestResultWrapper.Failure) -> Unit)? = null
) {
    flow.collect {
        when (this) {
            is BaseActivity -> handleRestResultWrapper(it, { result ->
                onEachValue(result.value)
            })
            is BaseFragment -> handleRestResultWrapper(it, { result ->
                onEachValue(result.value)
            })
            else -> {
                if (it is RestResultWrapper.Success) {
                    onEachValue(it.value)
                } else if (it is RestResultWrapper.Failure) {
                    onFailure?.invoke(it)
                }
            }
        }
    }
}
