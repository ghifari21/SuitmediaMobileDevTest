package com.gosty.suitmediamobiledevtest.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class CommonResponse<T>(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("per_page")
    val perPage: Int,

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("data")
    val data: List<T>
)
