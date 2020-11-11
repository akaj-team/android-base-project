package com.android.appname.di

import androidx.lifecycle.ViewModel
import com.android.appname.ui.main.MainActivity
import com.android.appname.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * @author at-hungtruong
 */
@Module
abstract class ActivityModules {
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    abstract fun mainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
