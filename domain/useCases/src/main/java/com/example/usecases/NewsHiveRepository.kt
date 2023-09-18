package com.example.usecases

import com.example.entities.news.LatestNews

interface NewsHiveRepository {
    suspend fun getLatestNews(
        sort: String,
        countries: String,
        language: String
    ): List<LatestNews>

    suspend fun getCategoryNews(
        categoryName: String,
        sort: String,
        countries: String,
        language: String
    ): List<LatestNews>
}