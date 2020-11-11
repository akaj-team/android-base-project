package com.android.appname.ui.base

import com.android.appname.di.MainViewModelFactory
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: MainViewModelFactory

    private val subscription: CompositeDisposable = CompositeDisposable()

    /**
     * This function is used to define subscription
     */
    abstract fun onBindViewModel()

    override fun onResume() {
        super.onResume()
        onBindViewModel()
    }

    override fun onPause() {
        super.onPause()
        subscription.clear()
    }

    protected fun addDisposables(vararg ds: Disposable) {
        ds.forEach { subscription.add(it) }
    }
}
