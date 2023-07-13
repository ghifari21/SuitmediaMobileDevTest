package com.gosty.suitmediamobiledevtest.core.utils

import com.gosty.suitmediamobiledevtest.core.data.remote.response.UserResponse
import com.gosty.suitmediamobiledevtest.core.domain.models.UserModel

object DataMapper {
    fun mapUserResponseToUserModel(input: UserResponse): UserModel =
        UserModel(
            id = input.id,
            email = input.email,
            firstName = input.firstName,
            lastName = input.lastName,
            avatar = input.avatar
        )
}