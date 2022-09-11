package com.flexcode.devspace.github.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.flexcode.devspace.github.domain.model.User
import com.flexcode.devspace.github.domain.use_cases.GetUserUseCase
import com.flexcode.devspace.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GetUserDetailsViewModel @Inject constructor(
    private val useCase: GetUserUseCase
): ViewModel(){

    fun getUserDetails(username: String): Flow<Resource<User>>{
        return useCase.invoke(username)
    }
}