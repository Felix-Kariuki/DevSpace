package com.flexcode.devspace.github.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RepositoryEntity(
    val created_at: String?,
    val description: String?,
    val forks: Int?,
    val full_name: String?,
    @PrimaryKey val id: Int?,
    val language: String?,
    val name: String?,
    val open_issues: Int?,
    @Embedded(prefix = "owner_info")
    val owner: OwnerEntity?,
    val size: Int?,
    val stargazers_count: Int?,
    val updated_at: String?,
    val visibility: String?,
)
