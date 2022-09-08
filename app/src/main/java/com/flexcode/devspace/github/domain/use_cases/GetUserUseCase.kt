package com.flexcode.devspace.github.domain.use_cases

import com.flexcode.devspace.github.domain.model.User
import com.flexcode.devspace.github.domain.repository.GetUserRepository
import com.flexcode.devspace.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetUserUseCase(private val getUserRepository: GetUserRepository) {

    operator fun invoke(user:String): Flow<Resource<User>>{
        return getUserRepository.getUserDetails(user)
    }
}