package com.flexcode.devspace.github.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FollowersEntity(
    val avatar_url: String?,
    @PrimaryKey val id: Int?,
    val login: String?,
)