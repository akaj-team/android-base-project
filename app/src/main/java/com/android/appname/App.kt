package com.android.appname

import com.android.appname.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.factory().create(applicationContext)

    override fun onCreate() {
        super.onCreate()
        RxJavaPlugins.setErrorHandler { }
    }
}
