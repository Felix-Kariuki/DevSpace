package com.flexcode.devspace.quotes.domain.repository

import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.quotes.domain.models.Quotes
import kotlinx.coroutines.flow.Flow

interface GetAllQuotesRepository {

    fun  getAllQuotes() : Flow<Resource<List<Quotes>>>
}