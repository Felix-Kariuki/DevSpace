package com.flexcode.devspace.quotes.data.mapper

import com.flexcode.devspace.quotes.data.local.entity.QuotesEntity
import com.flexcode.devspace.quotes.domain.models.Quotes

internal fun QuotesEntity.toDomain(): Quotes {
    return Quotes(
        author = author,
        en = en,
        id = id
    )
}