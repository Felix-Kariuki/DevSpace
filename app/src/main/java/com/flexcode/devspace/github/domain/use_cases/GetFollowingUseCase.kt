package com.flexcode.devspace.github.domain.use_cases

import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.github.domain.model.Following
import com.flexcode.devspace.github.domain.repository.GetFollowingRepository
import kotlinx.coroutines.flow.Flow

class GetFollowingUseCase(private val repository: GetFollowingRepository) {

    operator fun invoke(username: String): Flow<Resource<List<Following>>> {
        return repository.getFollowing(username)
    }
}
