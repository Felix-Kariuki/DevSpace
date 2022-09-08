package com.flexcode.devspace.github.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Following(
    val avatar_url: String?,
    val id: Int?,
    val login: String?,
)
