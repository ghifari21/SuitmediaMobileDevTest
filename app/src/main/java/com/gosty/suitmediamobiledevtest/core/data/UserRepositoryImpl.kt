package com.gosty.suitmediamobiledevtest.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gosty.suitmediamobiledevtest.core.data.remote.RemoteDataSource
import com.gosty.suitmediamobiledevtest.core.domain.models.UserModel
import com.gosty.suitmediamobiledevtest.core.domain.repositories.UserRepository
import com.gosty.suitmediamobiledevtest.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : UserRepository {
    override fun getUsers(): Flow<PagingData<UserModel>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10
            ),
            pagingSourceFactory = {
                remoteDataSource.getUsers()
            }
        ).flow
            .map { data ->
                data.map { response ->
                    DataMapper.mapUserResponseToUserModel(response)
                }
            }
}