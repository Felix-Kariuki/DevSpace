package com.flexcode.devspace.quotes.data.repository

import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.quotes.data.local.dao.QuotesDao
import com.flexcode.devspace.quotes.data.mapper.toDomain
import com.flexcode.devspace.quotes.data.mapper.toEntity
import com.flexcode.devspace.quotes.data.remote.QuotesApi
import com.flexcode.devspace.quotes.domain.models.Quotes
import com.flexcode.devspace.quotes.domain.repository.GetAllQuotesRepository
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetAllQuotesRepositoryImpl(
    private val dao: QuotesDao,
    private val api: QuotesApi
) : GetAllQuotesRepository {

    override fun getAllQuotes(): Flow<Resource<List<Quotes>>> = flow {

        emit(Resource.Loading())

        val quotesFromCache = dao.getAllQuotes().map { it.toDomain() }
        emit(Resource.Loading(data = quotesFromCache))

        try {
            val remoteQuotes = api.getAllQuotes()
            dao.deleteQuotes()
            dao.insertQuotes(remoteQuotes.map { it.toEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Something went wrong",
                    data = quotesFromCache
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "ERROR!!, check your internet connection!",
                    data = quotesFromCache
                )
            )
        }

        val newQuotes = dao.getAllQuotes().map { it.toDomain() }
        emit(Resource.Success(newQuotes))
    }
}
