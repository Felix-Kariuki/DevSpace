package com.flexcode.devspace.wakatime.domain.usecases

import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.wakatime.domain.models.Editors
import com.flexcode.devspace.wakatime.domain.repository.GetEditorsRepository
import kotlinx.coroutines.flow.Flow

class GetEditorsUseCase(
    private val repository: GetEditorsRepository
) {

    operator fun invoke(token: String): Flow<Resource<List<Editors>>>{
        return repository.getEditors(token)
    }
}