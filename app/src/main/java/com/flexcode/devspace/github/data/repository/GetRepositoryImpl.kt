package com.flexcode.devspace.github.data.repository

import com.flexcode.devspace.github.data.local.dao.RepositoryDao
import com.flexcode.devspace.github.data.mapper.toDomain
import com.flexcode.devspace.github.data.mapper.toEntity
import com.flexcode.devspace.github.data.remote.GitApi
import com.flexcode.devspace.github.domain.model.Repository
import com.flexcode.devspace.github.domain.repository.GetReposRepository
import com.flexcode.devspace.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetRepositoryImpl(
    private val dao:RepositoryDao ,
    private val api: GitApi,
) : GetReposRepository {
    override fun getRepos(username: String?): Flow<Resource<List<Repository>>> = flow {
        emit(Resource.Loading())


        val localRepos = dao.getRepositories().map { it.toDomain() }
        emit(Resource.Loading(data = localRepos))

        try {
            val remoteRepos = api.getUsersRepos(username.toString())
            dao.deleteRepositories()
            dao.insertRepositories(remoteRepos.map { it.toEntity() })

        }catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Something went wrong",
                    data = localRepos
                )
            )
        }catch (e: IOException){
            emit(
                Resource.Error(
                    message = "ERROR!!, check your internet connection!",
                    data = localRepos
                )
            )
        }

        val newRepos = dao.getRepositories().map { it.toDomain() }
        emit(Resource.Success(newRepos))
    }
}