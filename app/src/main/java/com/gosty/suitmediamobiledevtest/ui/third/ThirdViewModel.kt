package com.gosty.suitmediamobiledevtest.ui.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gosty.suitmediamobiledevtest.core.domain.models.UserModel
import com.gosty.suitmediamobiledevtest.core.domain.usecases.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for ThirdActivity
 */
@HiltViewModel
class ThirdViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {
    fun getUsers(): LiveData<PagingData<UserModel>> =
        userUseCase
            .getUsers()
            .cachedIn(viewModelScope)
            .asLiveData()
}