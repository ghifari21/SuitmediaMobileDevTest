package com.gosty.suitmediamobiledevtest.core.data.remote.network

import com.gosty.suitmediamobiledevtest.core.data.remote.response.CommonResponse
import com.gosty.suitmediamobiledevtest.core.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service to get data from API
 */
interface ApiService {
    @GET("users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): CommonResponse<UserResponse>
}