package com.android.appname.di

import android.content.Context
import androidx.room.Room
import com.android.appname.BuildConfig
import com.android.appname.managers.AppDatabaseManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author at-hungtruong
 */
@Module
class StorageModule {
    companion object {
        private const val DATABASE_NAME = "${BuildConfig.APPLICATION_ID}.database"
    }

    @Provides
    @Singleton
    fun provideAppDatabaseManager(context: Context) =
        Room.databaseBuilder(context, AppDatabaseManager::class.java, DATABASE_NAME).build()

    @Provides
    @Singleton
    fun provideRepositoryDao(appDatabaseManager: AppDatabaseManager) =
        appDatabaseManager.repositoryDao()

    @Provides
    @Singleton
    fun provideRepositoryOwnerDao(appDatabaseManager: AppDatabaseManager) =
        appDatabaseManager.repositoryOwnerDao()

    @Provides
    @Singleton
    fun provideLicenseDao(appDatabaseManager: AppDatabaseManager) =
        appDatabaseManager.licenseDao()
}
