package com.example.repositories.paging

import com.example.entities.news.NewsItemEntity
import com.example.repositories.mapper.toNewsItemEntity
import com.example.repositories.remote.RemoteDataStore
import javax.inject.Inject

class LatestNewsPagingSource @Inject constructor(
    remoteDataStore: RemoteDataStore,
    private val sort: String,
    private val language: String
) : BasePagingSource<NewsItemEntity>(remoteDataStore) {
    override suspend fun fetchData(offset: Int): List<NewsItemEntity>? {
        return remoteDataStore.getLatestNews(
            sort = sort,
            language = language,
            offset = offset
        ).data?.map { it.toNewsItemEntity() }
    }

    override suspend fun getTotalCount(offset: Int): Int? {
        return remoteDataStore.getLatestNews(
            sort = sort,
            language = language,
            offset = offset
        ).pagination?.total
    }
}