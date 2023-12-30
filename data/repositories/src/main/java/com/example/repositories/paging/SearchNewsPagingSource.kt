package com.example.repositories.paging

import com.example.entities.news.NewsItemEntity
import com.example.repositories.mapper.toNewsItemEntity
import com.example.repositories.remote.RemoteDataStore
import javax.inject.Inject

class SearchNewsPagingSource @Inject constructor(
    remoteDataStore: RemoteDataStore,
    val keyword: String,
    val language: String,
    val sort: String
) : BasePagingSource<NewsItemEntity>(remoteDataStore) {
    override suspend fun fetchData(offset: Int): List<NewsItemEntity>? {
        return remoteDataStore
            .searchNews(
                keyword=keyword,
                language = language,
                sort = sort,
                offset = offset
            ).data?.map { it.toNewsItemEntity() }
    }

    override suspend fun getTotalCount(offset: Int): Int? {
        return remoteDataStore
            .searchNews(
                keyword=keyword,
                language = language,
                sort = sort,
                offset = offset
            ).pagination?.total
    }
}