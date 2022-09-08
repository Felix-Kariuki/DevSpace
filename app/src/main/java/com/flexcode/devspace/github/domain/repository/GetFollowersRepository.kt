package com.flexcode.devspace.github.domain.repository

import com.flexcode.devspace.github.domain.model.Followers
import com.flexcode.devspace.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetFollowersRepository {

     fun getFollowers(username:String?): Flow<Resource<List<Followers>>>
}