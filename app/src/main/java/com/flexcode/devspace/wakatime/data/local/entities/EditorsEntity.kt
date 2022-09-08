package com.flexcode.devspace.wakatime.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EditorsEntity(
    val color: String?,
    @PrimaryKey val id: String,
    val name: String?,
    val released: Boolean?,
    val repository: String?,
    val version: String?
)
