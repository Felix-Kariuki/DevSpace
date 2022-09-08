package com.flexcode.devspace.wakatime.data.repository

import com.flexcode.devspace.core.utils.Resource
import com.flexcode.devspace.wakatime.data.local.dao.EditorsDao
import com.flexcode.devspace.wakatime.data.mappers.toDomain
import com.flexcode.devspace.wakatime.data.mappers.toEntity
import com.flexcode.devspace.wakatime.data.remote.WakatimeApi
import com.flexcode.devspace.wakatime.domain.models.Editors
import com.flexcode.devspace.wakatime.domain.repository.GetEditorsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class GetEditorsRepositoryImpl(
    private val api: WakatimeApi,
    private val dao : EditorsDao
) : GetEditorsRepository {
    override fun getEditors(token: String): Flow<Resource<List<Editors>>> = flow{

        emit(Resource.Loading())

        val localEditors = dao.getEditors().map { it.toDomain() }
        emit(Resource.Loading(data = localEditors))

        try {
            val remoteEditors = api.getEditors(token)
            dao.deleteEditors(remoteEditors.data.map { it?.repository })
            dao.insertEditors(remoteEditors.data.map { it?.toEntity() })
        }catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Something went wrong",
                    data = localEditors
                )
            )
        }catch (e: IOException){
            emit(
                Resource.Error(
                    message = "ERROR!!, check your internet connection!",
                    data = localEditors
                )
            )
        }

        val newEditors = dao.getEditors().map { it.toDomain() }
        emit(Resource.Success(newEditors))



    }.flowOn(Dispatchers.IO)
}