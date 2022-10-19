package com.flexcode.devspace.github.domain.repository

import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.github.domain.model.Following
import kotlinx.coroutines.flow.Flow

interface GetFollowingRepository {

    fun getFollowing(username: String?): Flow<Resource<List<Following>>>
}
