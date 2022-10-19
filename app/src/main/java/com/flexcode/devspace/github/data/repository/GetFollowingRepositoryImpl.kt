package com.flexcode.devspace.github.data.repository

import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.github.data.local.dao.FollowingDao
import com.flexcode.devspace.github.data.mapper.toDomain
import com.flexcode.devspace.github.data.mapper.toEntity
import com.flexcode.devspace.github.data.remote.GitApi
import com.flexcode.devspace.github.domain.model.Following
import com.flexcode.devspace.github.domain.repository.GetFollowingRepository
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetFollowingRepositoryImpl(
    private val dao: FollowingDao,
    private val api: GitApi
) : GetFollowingRepository {

    override fun getFollowing(username: String?): Flow<Resource<List<Following>>> = flow {

        emit(Resource.Loading())

        val followingInCache = dao.getFollowing().map { it.toDomain() }
        emit(Resource.Loading(data = followingInCache))

        try {
            val remoteFollowing = api.getFollowing(username.toString())
            dao.deleteFollowing()
            dao.insertFollowing(remoteFollowing.map { it.toEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Something went wrong",
                    data = followingInCache
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "ERROR!!, check your internet connection!",
                    data = followingInCache
                )
            )
        }

        val newFollowing = dao.getFollowing().map { it.toDomain() }
        emit(Resource.Success(newFollowing))
    }
}
