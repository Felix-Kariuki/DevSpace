package com.flexcode.devspace.github.domain.use_cases

import com.flexcode.devspace.github.domain.model.Followers
import com.flexcode.devspace.github.domain.repository.GetFollowersRepository
import com.flexcode.devspace.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetFollowersUseCase(private val repository: GetFollowersRepository) {

    operator fun invoke(username: String): Flow<Resource<List<Followers>>>{
        return repository.getFollowers(username)
    }
}