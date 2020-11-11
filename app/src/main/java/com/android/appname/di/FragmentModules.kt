package com.android.appname.di

import androidx.lifecycle.ViewModel
import com.android.appname.ui.git.repo.GitRepositoryFragment
import com.android.appname.ui.git.repo.GitRepositoryViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * @author at-hungtruong
 */
@Module
abstract class FragmentModules {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    abstract fun gitRepositoryFragment(): GitRepositoryFragment

    @Binds
    @IntoMap
    @ViewModelKey(GitRepositoryViewModel::class)
    abstract fun bindGitRepositoryViewModel(viewModel: GitRepositoryViewModel): ViewModel
}
