package com.flexcode.devspace.quotes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flexcode.devspace.quotes.data.local.dao.QuotesDao
import com.flexcode.devspace.quotes.data.local.entity.QuotesEntity


@Database(entities = [QuotesEntity::class], version = 1, exportSchema = false)
abstract class QuotesDatabase  : RoomDatabase(){

    abstract val quotesDao: QuotesDao
}