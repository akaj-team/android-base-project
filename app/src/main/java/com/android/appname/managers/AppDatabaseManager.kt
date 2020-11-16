package com.android.appname.managers

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.appname.data.entities.LicenseEntity
import com.android.appname.data.entities.RepositoryEntity
import com.android.appname.data.entities.RepositoryOwnerEntity
import com.android.appname.data.source.local.daos.LicenseDao
import com.android.appname.data.source.local.daos.RepositoryDao
import com.android.appname.data.source.local.daos.RepositoryOwnerDao

/**
 * @author at-hungtruong
 */
@Database(
    entities = [
        RepositoryEntity::class,
        LicenseEntity::class,
        RepositoryOwnerEntity::class
    ], version = 1
)
abstract class AppDatabaseManager : RoomDatabase() {
    abstract fun repositoryDao(): RepositoryDao

    abstract fun licenseDao(): LicenseDao

    abstract fun repositoryOwnerDao(): RepositoryOwnerDao
}
