package com.example.usecases

import com.example.entities.news.NewsItemEntity

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
}