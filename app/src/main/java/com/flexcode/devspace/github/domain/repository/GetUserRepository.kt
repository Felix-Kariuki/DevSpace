package com.flexcode.devspace.github.domain.repository

import com.flexcode.devspace.github.domain.model.User
import com.flexcode.devspace.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetUserRepository {
    fun getUserDetails(username: String?): Flow<Resource<User>>
}