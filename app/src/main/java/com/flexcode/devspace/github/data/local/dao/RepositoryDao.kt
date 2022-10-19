package com.flexcode.devspace.github.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexcode.devspace.github.data.local.entities.RepositoryEntity

@Dao
interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepositories(repos: List<RepositoryEntity>)

    @Query("DELETE FROM repositoryentity")
    suspend fun deleteRepositories()

    @Query("SELECT * FROM repositoryentity")
    suspend fun getRepositories(): List<RepositoryEntity>
}
