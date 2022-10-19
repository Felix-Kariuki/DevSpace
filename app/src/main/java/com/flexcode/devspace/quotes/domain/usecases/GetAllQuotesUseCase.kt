package com.flexcode.devspace.quotes.domain.usecases

import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.quotes.domain.models.Quotes
import com.flexcode.devspace.quotes.domain.repository.GetAllQuotesRepository
import kotlinx.coroutines.flow.Flow

class GetAllQuotesUseCase(private val repository: GetAllQuotesRepository) {

    operator fun invoke(): Flow<Resource<List<Quotes>>> {
        return repository.getAllQuotes()
    }
}
