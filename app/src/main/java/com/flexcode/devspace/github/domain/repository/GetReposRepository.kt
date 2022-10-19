package com.flexcode.devspace.github.domain.repository

import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.github.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface GetReposRepository {
    fun getRepos(username: String?): Flow<Resource<List<Repository>>>
}
