package com.example.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.repositories.remote.RemoteDataStore


abstract class BasePagingSource<value : Any>(
    protected val remoteDataStore: RemoteDataStore
) : PagingSource<Int, value>() {
    protected abstract suspend fun fetchData(offset: Int): List<value>?
    protected abstract suspend fun getTotalCount(offset: Int): Int?
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, value> {
        return try {
            val offset = params.key ?: STARTING_PAGE_INDEX
            val data = fetchData(offset) ?: emptyList()
            val totalCount = getTotalCount(offset) ?: STARTING_PAGE_INDEX
            LoadResult.Page(
                data = data,
                prevKey = if (offset > STARTING_PAGE_INDEX) offset - PAGE_SIZE else null,
                nextKey = if (offset + PAGE_SIZE < totalCount) offset + PAGE_SIZE else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, value>): Int? {
        return null
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 0
        private const val PAGE_SIZE = 25
    }
}

