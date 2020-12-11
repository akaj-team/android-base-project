package com.android.appname.di

import android.content.Context
import com.android.appname.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author at-hungtruong
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityModules::class,
        FragmentModules::class,
        NetworkModule::class,
        StorageModule::class,
        PreferenceModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}