package com.flexcode.devspace.github.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.flexcode.devspace.github.domain.model.Followers
import com.flexcode.devspace.github.domain.use_cases.GetFollowersUseCase
import com.flexcode.devspace.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GetFollowersViewModel @Inject constructor(
    private val getFollowersUseCase: GetFollowersUseCase
) :ViewModel() {

    fun getFollowers(username:String):Flow<Resource<List<Followers>>>{
        return getFollowersUseCase.invoke(username)
    }
}