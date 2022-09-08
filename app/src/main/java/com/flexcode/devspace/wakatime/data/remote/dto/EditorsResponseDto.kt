package com.flexcode.devspace.wakatime.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class EditorsResponseDto(
    val `data`: List<EditorsDto?>,
    val total: Int,
    val total_pages: Int
)