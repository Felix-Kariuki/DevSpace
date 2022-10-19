package com.flexcode.devspace.github.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class OwnerDto(
    val avatar_url: String?,
    val login: String?,
)
