package com.android.appname.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.appname.di.MainViewModelFactory
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    @set:Inject
    var androidInjector: DispatchingAndroidInjector<Any?>? = null

    @Inject
    protected lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun androidInjector() = androidInjector
}
