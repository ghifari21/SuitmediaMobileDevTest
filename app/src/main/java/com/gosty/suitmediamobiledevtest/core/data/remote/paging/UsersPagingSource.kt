package com.gosty.suitmediamobiledevtest.core.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gosty.suitmediamobiledevtest.core.data.remote.network.ApiService
import com.gosty.suitmediamobiledevtest.core.data.remote.response.UserResponse
import retrofit2.HttpException
import java.io.IOException

/**
 * Service for getting paging data
 */
class UsersPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, UserResponse>() {
    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int? {
        return state.anchorPosition?.let { pos ->
            val anchorPage = state.closestPageToPosition(pos)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getUsers(page = position, perPage = PER_PAGE)

            LoadResult.Page(
                data = responseData.data,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.data.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
        const val PER_PAGE = 10
    }
}