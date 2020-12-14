package com.android.appname.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.appname.data.model.RestResultWrapper
import com.android.appname.di.MainViewModelFactory
import com.android.appname.managers.PrefManager
import com.android.appname.ui.dialog.loading.LoadingDialogFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    @set:Inject
    var androidInjector: DispatchingAndroidInjector<Any?>? = null

    @set:Inject
    var preferencesManager: PrefManager? = null

    private val loadingDialog = LoadingDialogFragment.newInstance()

    @Inject
    protected lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector() = androidInjector

    internal fun setLoadingDialogVisibility(isVisible: Boolean) {
        if (isVisible) {
            loadingDialog.show(supportFragmentManager, LoadingDialogFragment::class.java.name)
        } else {
            loadingDialog.dismissAllowingStateLoss()
        }
    }

    internal open fun onApiFailure(failure: RestResultWrapper.Failure) {
        // TODO: 12/14/20 Show global dialog
        failure.throwable?.printStackTrace()
    }

    internal open fun onApiUnauthorized() {
        // TODO: 12/14/20 Handle unauthorized error
        preferencesManager?.clear()
    }

    internal fun <T> handleApiResult(
        result: RestResultWrapper<T>,
        onSuccess: ((RestResultWrapper<T>) -> Unit),
        onFailure: ((RestResultWrapper.Failure) -> Unit) = this::onApiFailure,
        onUnauthorized: (() -> Unit) = this::onApiUnauthorized
    ) {
        when (result) {
            is RestResultWrapper.Success -> {
                onSuccess(result)
            }
            is RestResultWrapper.UnauthorizedError -> {
                onUnauthorized()
            }
            is RestResultWrapper.Failure -> {
                onFailure(result)
            }
        }
    }
}
