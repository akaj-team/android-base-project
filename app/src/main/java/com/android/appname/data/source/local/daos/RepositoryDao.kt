package com.android.appname.data.source.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.appname.data.entities.RepositoryEntity

/**
 * @author at-hungtruong
 */
@Dao
interface RepositoryDao {
    @Query("SELECT * FROM RepositoryEntity")
    suspend fun getRepositories(): List<RepositoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg repositories: RepositoryEntity): List<Long>
}
