package com.flexcode.devspace.wakatime.data.mappers

import androidx.room.PrimaryKey
import com.flexcode.devspace.wakatime.data.local.entities.EditorsEntity
import com.flexcode.devspace.wakatime.data.remote.dto.EditorsDto

internal fun EditorsDto.toEntity(): EditorsEntity {

    return EditorsEntity(
        color = color,
        id = id!!,
        name = name,
        released = released,
        repository = repository,
        version = version
    )
}