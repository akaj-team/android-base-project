package com.android.appname.di

import com.android.appname.data.source.GitRepository
import com.android.appname.data.source.datasource.GitDataSource
import com.android.appname.data.source.local.GitLocalDataSource
import com.android.appname.data.source.remote.GitRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * @author at-hungtruong
 */
@Module(
    includes = [
        ApplicationModuleBinds::class
    ]
)
object ApplicationModule {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class GitRemoteDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class GitLocalDataSource

    @Singleton
    @GitRemoteDataSource
    @Provides
    fun provideGitRemoteDataSource(): GitDataSource = GitRemoteDataSource

    @Singleton
    @GitLocalDataSource
    @Provides
    fun provideGitLocalDataSource(): GitDataSource = GitLocalDataSource
}

@Module
abstract class ApplicationModuleBinds {
    @Singleton
    @Binds
    abstract fun bindGitRepository(repository: GitRepository): GitDataSource
}
