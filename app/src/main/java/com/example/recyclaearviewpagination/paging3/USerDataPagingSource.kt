package com.example.recyclaearviewpagination.paging3


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recyclaearviewpagination.model.Data
import com.example.recyclaearviewpagination.networking.ApiClient
import com.example.recyclaearviewpagination.networking.ApiService

class USerDataPagingSource : PagingSource<Int, Data>() {
    private val apiService = ApiClient.getRetrofit().create(ApiService::class.java)


    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        try {
            var nextPAgeNumber = params.key ?: 1
            val response = apiService.getPaging3Users(nextPAgeNumber)

            if (response.isSuccessful) {
                return LoadResult.Page(response.body()?.data ?: emptyList(), null, ++nextPAgeNumber)
            } else {
                return LoadResult.Error(Throwable("Error"))
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}