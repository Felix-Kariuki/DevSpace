package com.flexcode.devspace.wakatime.data.mappers

import com.flexcode.devspace.wakatime.data.local.entities.EditorsEntity
import com.flexcode.devspace.wakatime.domain.models.Editors

internal fun EditorsEntity.toDomain(): Editors {
    return Editors(
        color = color,
        id = id,
        name = name,
        released = released,
        repository = repository,
        version = version
    )
}