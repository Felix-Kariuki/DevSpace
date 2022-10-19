package com.flexcode.devspace.quotes.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuotesDto(
    val author: String,
    val en: String,
    val id: String
)
