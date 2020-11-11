package com.android.appname.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.appname.di.MainViewModelFactory
import com.android.appname.ui.dialog.loading.LoadingDialogFragment
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    @set:Inject
    var androidInjector: DispatchingAndroidInjector<Any?>? = null
    protected val loadingDialog = LoadingDialogFragment.newInstance()

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
}
