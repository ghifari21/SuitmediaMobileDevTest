package com.gosty.suitmediamobiledevtest.core.domain.usecases

import androidx.paging.PagingData
import com.gosty.suitmediamobiledevtest.core.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

/**
 * Use Case contract
 */
interface UserUseCase {
    fun getUsers(): Flow<PagingData<UserModel>>
}