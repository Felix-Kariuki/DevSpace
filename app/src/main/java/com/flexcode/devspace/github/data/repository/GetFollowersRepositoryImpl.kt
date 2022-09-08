package com.flexcode.devspace.github.data.repository

import com.flexcode.devspace.github.data.local.dao.FollowersDao
import com.flexcode.devspace.github.data.mapper.toDomain
import com.flexcode.devspace.github.data.mapper.toEntity
import com.flexcode.devspace.github.data.remote.GitApi
import com.flexcode.devspace.github.domain.model.Followers
import com.flexcode.devspace.github.domain.repository.GetFollowersRepository
import com.flexcode.devspace.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetFollowersRepositoryImpl(
    private val dao:FollowersDao,
    private val api: GitApi
): GetFollowersRepository {

    override fun getFollowers(username: String?): Flow<Resource<List<Followers>>> = flow {

        emit(Resource.Loading())

        val followersFromCache = dao.getUserFollowers().map { it.toDomain() }
        emit(Resource.Loading(data = followersFromCache))

        try {
            val remoteFollowers = api.getFollowers(username.toString())
            dao.deleteFollowers()
            dao.insertFollowers(remoteFollowers.map { it.toEntity() })
        }catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Something went wrong",
                    data = followersFromCache
                )
            )
        }catch (e: IOException){
            emit(
                Resource.Error(
                    message = "ERROR!!, check your internet connection!",
                    data = followersFromCache
                )
            )
        }

        val newFollowers = dao.getUserFollowers().map { it.toDomain() }
        emit(Resource.Success(newFollowers))
    }
}