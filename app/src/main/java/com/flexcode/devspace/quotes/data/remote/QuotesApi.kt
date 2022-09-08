package com.flexcode.devspace.quotes.data.remote

import com.flexcode.devspace.quotes.data.remote.dto.QuotesDto
import retrofit2.http.GET

interface QuotesApi {

    @GET("/quotes")
    suspend fun getAllQuotes() : List<QuotesDto>
}