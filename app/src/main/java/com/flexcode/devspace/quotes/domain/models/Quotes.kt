package com.flexcode.devspace.quotes.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Quotes(
    val author: String,
    val en: String,
    val id: String
)