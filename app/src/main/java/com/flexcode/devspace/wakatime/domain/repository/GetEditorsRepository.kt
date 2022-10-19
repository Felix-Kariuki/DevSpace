package com.flexcode.devspace.wakatime.domain.repository

import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.wakatime.domain.models.Editors
import kotlinx.coroutines.flow.Flow

interface GetEditorsRepository {

    fun getEditors(token: String): Flow<Resource<List<Editors>>>
}
