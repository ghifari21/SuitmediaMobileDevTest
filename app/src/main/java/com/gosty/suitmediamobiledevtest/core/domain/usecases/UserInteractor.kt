package com.gosty.suitmediamobiledevtest.core.domain.usecases

import androidx.paging.PagingData
import com.gosty.suitmediamobiledevtest.core.domain.models.UserModel
import com.gosty.suitmediamobiledevtest.core.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val repository: UserRepository
) : UserUseCase {
    override fun getUsers(): Flow<PagingData<UserModel>> = repository.getUsers()
}