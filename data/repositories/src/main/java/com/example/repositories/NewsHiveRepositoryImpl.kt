package com.example.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.entities.news.NewsItemEntity
import com.example.repositories.local.LocalDataStore
import com.example.repositories.mapper.toNewsItemEntities
import com.example.repositories.mapper.toNewsItemEntity
import com.example.repositories.mapper.toNewsLocalDto
import com.example.repositories.paging.CategoryNewsPagingSource
import com.example.repositories.paging.LatestNewsPagingSource
import com.example.repositories.paging.SearchNewsPagingSource
import com.example.repositories.remote.RemoteDataStore
import com.example.usecases.NewsHiveRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsHiveRepositoryImpl @Inject constructor(
    private val remoteDataStore: RemoteDataStore,
    private val localDataStore: LocalDataStore
) : NewsHiveRepository {
    override suspend fun getAllLatestNews(
        sort: String,
        language: String
    ): Flow<PagingData<NewsItemEntity>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { LatestNewsPagingSource(remoteDataStore, sort, language) }
        ).flow
    }

    override suspend fun getCategoryNews(
        categoryName: String,
        sort: String,
        language: String
    ): Flow<PagingData<NewsItemEntity>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                CategoryNewsPagingSource(remoteDataStore, categoryName, sort, language)
            }
        ).flow
    }

    override suspend fun saveNewsForLater(newsItemEntity: NewsItemEntity) {
        localDataStore.insertOrUpdateNews(newsItemEntity.toNewsLocalDto())
    }

    override suspend fun getSavedNews(): Flow<List<NewsItemEntity>> {
        return localDataStore.getAllSavedNews().map { it.toNewsItemEntities() }
    }

    override suspend fun deleteSavedNews(title: String) {
        localDataStore.deleteSavedNewsByTitle(title)
    }

    override suspend fun searchNews(
        keyword: String,
        language: String,
        sort: String
    ): Flow<PagingData<NewsItemEntity>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                SearchNewsPagingSource(remoteDataStore, keyword, language, sort)
            }
        ).flow
    }

    override fun getCategoryNewsPaging(
        categoryName: String,
        sort: String,
        language: String
    ): Flow<PagingData<NewsItemEntity>> {
        return Pager(
            config = PagingConfig(pageSize = CATEGORY_PAGE_SIZE),
            pagingSourceFactory = {
                CategoryNewsPagingSource(remoteDataStore, categoryName, sort, language)
            }
        ).flow
    }

    override suspend fun getLatestNews(sort: String, language: String): List<NewsItemEntity> {
        return remoteDataStore.getLatestNews(
            sort = sort,
            language = language,
            offset = 0
        ).data?.map { it.toNewsItemEntity() } ?: emptyList()
    }

    companion object {
        const val PAGE_SIZE = 12
        const val CATEGORY_PAGE_SIZE = 5

    }

}
