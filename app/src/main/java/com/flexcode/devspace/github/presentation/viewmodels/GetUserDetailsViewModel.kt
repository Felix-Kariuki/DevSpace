package com.flexcode.devspace.github.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String?> = _searchQuery

    fun getUserDetails(username: String): Flow<Resource<User>>{
        _searchQuery.value = username
        return useCase.invoke(username)
    }
}