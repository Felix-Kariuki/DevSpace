package com.flexcode.devspace.quotes.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuotesEntity(
    val author: String,
    val en: String,
    @PrimaryKey val id: String
)