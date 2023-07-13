package com.gosty.suitmediamobiledevtest.core.domain.repositories

import androidx.paging.PagingData
import com.gosty.suitmediamobiledevtest.core.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository contract
 */
interface UserRepository {
    fun getUsers(): Flow<PagingData<UserModel>>
}