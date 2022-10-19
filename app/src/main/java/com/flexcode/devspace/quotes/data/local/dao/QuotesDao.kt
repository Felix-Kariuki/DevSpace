package com.flexcode.devspace.quotes.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexcode.devspace.quotes.data.local.entity.QuotesEntity

@Dao
interface QuotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(quotes: List<QuotesEntity>)

    @Query("DELETE FROM quotesentity")
    suspend fun deleteQuotes()

    @Query("SELECT * FROM quotesentity")
    suspend fun getAllQuotes(): List<QuotesEntity>
}
