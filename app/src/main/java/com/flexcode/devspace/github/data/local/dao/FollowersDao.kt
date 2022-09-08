package com.flexcode.devspace.github.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexcode.devspace.github.data.local.entities.FollowersEntity

@Dao
interface FollowersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFollowers(followers: List<FollowersEntity>)

    @Query("DELETE FROM followersentity")
    suspend fun deleteFollowers()

    @Query("SELECT * FROM followersentity")
    suspend fun getUserFollowers(): List<FollowersEntity>
}