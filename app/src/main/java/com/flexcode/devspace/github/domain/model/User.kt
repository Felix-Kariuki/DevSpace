package com.flexcode.devspace.github.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val avatar_url: String?,
    val bio: String?,
    val blog: String?,
    val email: String?,
    val followers: Int?,
    val following: Int?,
    val id: Int?,
    val location: String?,
    val login: String?,
    val name: String?,
    val public_repos: Int?,
    val twitter_username: String?,
    val url: String?
)
