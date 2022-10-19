package com.flexcode.devspace.github.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexcode.devspace.github.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("DELETE FROM userentity")
    suspend fun deleteUser()

    @Query("SELECT * FROM userentity where login =:query")
    suspend fun getUser(query: String?): UserEntity?
}
