package com.example.usecases

import androidx.paging.PagingData
import com.example.entities.news.NewsItemEntity
import kotlinx.coroutines.flow.Flow

interface NewsHiveRepository {
    suspend fun getAllLatestNews(
        sort: String,
        language: String
    ): Flow<PagingData<NewsItemEntity>>

    suspend fun getCategoryNews(
        categoryName: String,
        sort: String,
        language: String
    ): Flow<PagingData<NewsItemEntity>>

    suspend fun saveNewsForLater(newsItemEntity: NewsItemEntity)

    suspend fun getSavedNews(): Flow<List<NewsItemEntity>>

    suspend fun deleteSavedNews(title: String)

    suspend fun searchNews(
        keyword: String,
        language: String,
        sort: String
    ): Flow<PagingData<NewsItemEntity>>

     fun getCategoryNewsPaging(
        categoryName: String,
        sort: String,
        language: String
    ): Flow<PagingData<NewsItemEntity>>

   suspend fun getLatestNews(
        sort: String,
        language: String
    ): List<NewsItemEntity>
}