package com.gosty.suitmediamobiledevtest.core.data.remote

import androidx.paging.PagingSource
import com.gosty.suitmediamobiledevtest.core.data.remote.network.ApiService
import com.gosty.suitmediamobiledevtest.core.data.remote.paging.UsersPagingSource
import com.gosty.suitmediamobiledevtest.core.data.remote.response.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun getUsers(): PagingSource<Int, UserResponse> = UsersPagingSource(apiService)
}