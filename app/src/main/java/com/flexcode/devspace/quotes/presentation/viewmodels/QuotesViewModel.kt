package com.flexcode.devspace.quotes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.quotes.domain.models.Quotes
import com.flexcode.devspace.quotes.domain.usecases.GetAllQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val getAllQuotesUseCase: GetAllQuotesUseCase
) : ViewModel() {

    fun getAllQuotes(): Flow<Resource<List<Quotes>>> {
        return getAllQuotesUseCase.invoke()
    }
}
