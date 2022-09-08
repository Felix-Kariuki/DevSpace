package com.flexcode.devspace.github.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.flexcode.devspace.github.domain.model.Following
import com.flexcode.devspace.github.domain.use_cases.GetFollowingUseCase
import com.flexcode.devspace.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GetFollowingViewModel @Inject constructor(
    private val getFollowingUseCase: GetFollowingUseCase
) : ViewModel(){

    fun getFollowing(username: String): Flow<Resource<List<Following>>>{
        return getFollowingUseCase.invoke(username)
    }
}