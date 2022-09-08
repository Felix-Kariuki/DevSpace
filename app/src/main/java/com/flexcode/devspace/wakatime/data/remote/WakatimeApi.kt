package com.flexcode.devspace.wakatime.data.remote

import com.flexcode.devspace.wakatime.data.remote.dto.EditorsResponseDto
import retrofit2.http.GET
import retrofit2.http.Header

interface WakatimeApi {

    @GET("/api/v1/editors")
    suspend fun getEditors(
        @Header("Authorization") token: String
    ) : EditorsResponseDto


}