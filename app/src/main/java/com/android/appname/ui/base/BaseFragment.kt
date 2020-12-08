package com.android.appname.ui.base

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
}
