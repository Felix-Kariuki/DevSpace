package com.flexcode.devspace.wakatime.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flexcode.devspace.wakatime.data.local.dao.EditorsDao
import com.flexcode.devspace.wakatime.data.local.entities.EditorsEntity

@Database(entities = [EditorsEntity::class], version = 1, exportSchema = false)
abstract class WakatimeDatabase : RoomDatabase() {

    abstract val editorsDao: EditorsDao
}
