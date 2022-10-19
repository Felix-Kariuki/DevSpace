package com.flexcode.devspace.github.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.github.domain.model.Followers
import com.flexcode.devspace.github.domain.use_cases.GetFollowersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class GetFollowersViewModel @Inject constructor(
    private val getFollowersUseCase: GetFollowersUseCase
) : ViewModel() {

    fun getFollowers(username: String): Flow<Resource<List<Followers>>> {
        return getFollowersUseCase.invoke(username)
    }
}
