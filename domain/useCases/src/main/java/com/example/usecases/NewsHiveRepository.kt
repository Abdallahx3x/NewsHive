package com.example.usecases

import com.example.entities.news.NewsItemEntity
import kotlinx.coroutines.flow.Flow

interface NewsHiveRepository {
    suspend fun getLatestNews(
        sort: String,
        countries: String,
        language: String
    ): List<NewsItemEntity>?

    suspend fun getCategoryNews(
        categoryName: String,
        sort: String,
        countries: String,
        language: String
    ): List<NewsItemEntity>?

    suspend fun saveNewsForLater(newsItemEntity: NewsItemEntity)
    suspend fun getSavedNews(): Flow<List<NewsItemEntity>>

    suspend fun deleteSavedNews(title: String)

}