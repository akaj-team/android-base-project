package com.android.appname.ui.base

import com.android.appname.data.model.RestResultWrapper
import com.android.appname.di.MainViewModelFactory
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: MainViewModelFactory

    private val subscription: CompositeDisposable = CompositeDisposable()

    /**
     * This function is used to define subscription
     */
    open fun onBindViewModel() = Unit

    override fun onResume() {
        super.onResume()
        onBindViewModel()
    }

    override fun onPause() {
        super.onPause()
        subscription.clear()
    }

    internal fun setLoadingDialogVisibility(isVisible: Boolean) {
        (activity as? BaseActivity)?.setLoadingDialogVisibility(isVisible)
    }

    protected open fun onApiFailure(failure: RestResultWrapper.Failure) {
        // TODO: 12/14/20 Show global dialog
        (activity as? BaseActivity)?.onApiFailure(failure)
    }

    protected open fun onApiUnauthorized() {
        // TODO: 12/14/20 Handle unauthorized error
        (activity as? BaseActivity)?.onApiUnauthorized()
    }

    internal fun <T> handleApiResult(
        result: RestResultWrapper<T>,
        onSuccess: ((RestResultWrapper<T>) -> Unit),
        onFailure: ((RestResultWrapper.Failure) -> Unit) = this::onApiFailure,
        onUnauthorized: (() -> Unit) = this::onApiUnauthorized,
        doOnFinally: (() -> Unit)? = null
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
        doOnFinally?.invoke()
    }
}
