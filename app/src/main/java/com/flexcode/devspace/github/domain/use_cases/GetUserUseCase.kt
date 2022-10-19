package com.flexcode.devspace.github.domain.use_cases

import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.github.domain.model.User
import com.flexcode.devspace.github.domain.repository.GetUserRepository
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val getUserRepository: GetUserRepository) {

    operator fun invoke(username: String): Flow<Resource<User>> {
        return getUserRepository.getUserDetails(username)
    }
}
