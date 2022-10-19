package com.flexcode.devspace.github.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexcode.devspace.github.data.local.entities.FollowingEntity

@Dao
interface FollowingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFollowing(following: List<FollowingEntity>)

    @Query("DELETE FROM followingentity")
    suspend fun deleteFollowing()

    @Query("SELECT * FROM followingentity")
    suspend fun getFollowing(): List<FollowingEntity>
}
