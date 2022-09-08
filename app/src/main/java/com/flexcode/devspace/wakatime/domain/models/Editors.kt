package com.flexcode.devspace.wakatime.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Editors(
    val color: String?,
    val id: String?,
    val name: String?,
    val released: Boolean?,
    val repository: String?,
    val version: String?
)