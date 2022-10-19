package com.flexcode.devspace.wakatime.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class EditorsDto(
    val color: String?,
    val id: String?,
    val name: String?,
    val released: Boolean?,
    val repository: String?,
    val version: String?
)
