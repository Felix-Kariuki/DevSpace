package com.flexcode.devspace.wakatime.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.wakatime.domain.models.Editors
import com.flexcode.devspace.wakatime.domain.usecases.GetEditorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GetEditorsViewModel @Inject constructor(
    private val getEditorsUseCase: GetEditorsUseCase
) : ViewModel(){

    fun getAllEditors(token:String): Flow<Resource<List<Editors>>>{
        return getEditorsUseCase.invoke(token)
    }
}