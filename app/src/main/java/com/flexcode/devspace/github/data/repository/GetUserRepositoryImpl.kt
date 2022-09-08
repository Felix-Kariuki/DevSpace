package com.flexcode.devspace.github.data.repository

import com.flexcode.devspace.github.data.local.dao.UserDao
import com.flexcode.devspace.github.data.mapper.toDomain
import com.flexcode.devspace.github.data.mapper.toEntity
import com.flexcode.devspace.github.data.remote.GitApi
import com.flexcode.devspace.github.domain.model.User
import com.flexcode.devspace.github.domain.repository.GetUserRepository
import com.flexcode.devspace.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetUserRepositoryImpl(
    private val api: GitApi,
    private val dao: UserDao
) : GetUserRepository {

    override fun getUserDetails(username: String?): Flow<Resource<User>> = flow {
        emit(Resource.Loading())

        /**
         * get user details from cache
         */
        val userFromCache = dao.getUser(username)?.toDomain()
        emit(Resource.Loading(data = userFromCache))

        try {
            val remoteUser = api.getUser(username.toString())
            dao.deleteUser()
            dao.insertUser(remoteUser.toEntity())
            //remoteUser.let { dao.insertUser(it.toEntity()) }

        }catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Something went wrong",
                    data = userFromCache
                )
            )
        }catch (e: IOException){
            emit(
                Resource.Error(
                    message = "ERROR!!, check your internet connection!",
                    data = userFromCache
                )
            )
        }

        val newUser = dao.getUser(username)?.toDomain()
        emit(Resource.Success(newUser))
    }
}