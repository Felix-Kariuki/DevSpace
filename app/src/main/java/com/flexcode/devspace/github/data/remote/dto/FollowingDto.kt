package com.flexcode.devspace.github.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class FollowingDto(
    val avatar_url: String?,
    val id: Int?,
    val login: String?,
)
