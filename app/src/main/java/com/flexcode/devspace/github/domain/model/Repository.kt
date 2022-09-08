package com.flexcode.devspace.github.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val created_at: String?,
    val description: String?,
    val forks: Int?,
    val full_name: String?,
    val id: Int?,
    val language: String?,
    val name: String?,
    val open_issues: Int?,
    val owner: Owner?,
    val size: Int?,
    val stargazers_count: Int?,
    val updated_at: String?,
    val visibility: String?,
)
