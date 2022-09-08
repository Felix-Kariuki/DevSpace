package com.flexcode.devspace.github.domain.use_cases

import com.flexcode.devspace.github.domain.model.Repository
import com.flexcode.devspace.github.domain.repository.GetReposRepository
import com.flexcode.devspace.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetRepositoryUseCase(private val repository: GetReposRepository) {
    operator fun invoke(username:String): Flow<Resource<List<Repository>>>{
        return repository.getRepos(username)
    }
}