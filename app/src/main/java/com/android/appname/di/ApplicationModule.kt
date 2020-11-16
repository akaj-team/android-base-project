package com.android.appname.di

import com.android.appname.data.source.GitRepository
import com.android.appname.data.source.datasource.GitDataSource
import com.android.appname.data.source.local.GitLocalDataSource
import com.android.appname.data.source.remote.GitRemoteDataSource
import com.android.appname.data.source.remote.network.SafeApiCallService
import com.google.gson.Gson
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
    @Singleton
    @Provides
    fun provideSafeApiCallService(gson: Gson) = SafeApiCallService(gson)
}

@Module
abstract class ApplicationModuleBinds {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class GitRemoteDataSourceQualifier

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class GitLocalDataSourceQualifier

    @Singleton
    @Binds
    abstract fun bindGitRepository(repository: GitRepository): GitDataSource

    @Singleton
    @GitRemoteDataSourceQualifier
    @Binds
    abstract fun provideGitRemoteDataSource(remoteDataSource: GitRemoteDataSource): GitDataSource

    @Singleton
    @GitLocalDataSourceQualifier
    @Binds
    abstract fun provideGitLocalDataSource(localDataSource: GitLocalDataSource): GitDataSource
}
