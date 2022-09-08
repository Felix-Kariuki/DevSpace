package com.flexcode.devspace.github.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.flexcode.devspace.github.domain.model.Repository
import com.flexcode.devspace.github.domain.use_cases.GetRepositoryUseCase
import com.flexcode.devspace.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GetReposViewModel @Inject constructor(
    private val getRepositoryUseCase: GetRepositoryUseCase
) :ViewModel() {

    fun getUserRepositories(username:String):Flow<Resource<List<Repository>>>{
        return getRepositoryUseCase.invoke(username)
    }
}