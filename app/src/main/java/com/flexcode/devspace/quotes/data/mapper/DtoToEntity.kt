package com.flexcode.devspace.quotes.data.mapper

import com.flexcode.devspace.quotes.data.local.entity.QuotesEntity
import com.flexcode.devspace.quotes.data.remote.dto.QuotesDto

internal fun QuotesDto.toEntity(): QuotesEntity {
    return QuotesEntity(
        author = author,
        en = en,
        id = id
    )
}