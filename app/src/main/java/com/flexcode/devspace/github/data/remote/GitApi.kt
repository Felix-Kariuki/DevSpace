package com.flexcode.devspace.github.data.remote

import com.flexcode.devspace.BuildConfig
import com.flexcode.devspace.github.data.remote.dto.FollowersDto
import com.flexcode.devspace.github.data.remote.dto.FollowingDto
import com.flexcode.devspace.github.data.remote.dto.RepositoryDto
import com.flexcode.devspace.github.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GitApi {

    @GET("/users/{user}")
    suspend fun getUser(
        @Path("user") username: String,
        @Header("Authorization") accessToken: String = BuildConfig.ACCESS_TOKEN
    ): UserDto

    @GET("/users/{user}/followers")
    suspend fun getFollowers(
        @Path("user") username: String,
        @Header("Authorization") accessToken: String = BuildConfig.ACCESS_TOKEN
    ):List<FollowersDto>

    @GET("/users/{user}/following")
    suspend fun getFollowing(
        @Path("user") username: String,
        @Header("Authorization") accessToken: String = BuildConfig.ACCESS_TOKEN
    ): List<FollowingDto>

    @GET("/users/{user}/repos")
    suspend fun getUsersRepos(
        @Path("user") user: String,
        @Header("Authorization") accessToken: String = BuildConfig.ACCESS_TOKEN
    ): List<RepositoryDto>
}