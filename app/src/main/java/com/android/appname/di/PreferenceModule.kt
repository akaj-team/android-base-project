package com.android.appname.di

import com.android.appname.managers.PrefManager
import com.android.appname.managers.PrefManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * @author at-hungtruong
 */
@Module
abstract class PreferenceModule {

    @Binds
    @Singleton
    abstract fun bindSharedPreferences(manager: PrefManagerImpl): PrefManager
}
