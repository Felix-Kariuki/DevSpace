package com.flexcode.devspace.wakatime.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexcode.devspace.wakatime.data.local.entities.EditorsEntity

@Dao
interface EditorsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEditors(editors: List<EditorsEntity?>)

    @Query("DELETE FROM editorsentity WHERE repository IN(:editors)")
    suspend fun deleteEditors(editors: List<String?>)

    @Query("SELECT * FROM EditorsEntity WHERE repository OR name LIKE '%'  || '%'")
    suspend fun getEditors(): List<EditorsEntity>
}
