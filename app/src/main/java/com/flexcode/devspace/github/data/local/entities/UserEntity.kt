package com.flexcode.devspace.github.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    val avatar_url: String?,
    val bio: String?,
    val blog: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    @PrimaryKey val id: Int?,
    val location: String?,
    val login: String?,
    val name: String?,
    val public_repos: Int?,
    val twitter_username: String?,
    val url: String?
)