package com.flexcode.devspace.github.data.local.entities

import androidx.room.ColumnInfo

data class OwnerEntity(
    @ColumnInfo(name = "avatar_url")
    val avatar_url: String?,
    val login: String?,
)
