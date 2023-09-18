package com.example.repositories

import com.example.repositories.dto.NewsResponseDto

interface RemoteDataStore {
    suspend fun getLatestNews(
        sort: String,
        countries: String,
        language: String
    ): NewsResponseDto

    suspend fun getCategoryNews(
        categoryName: String,
        sort: String,
        countries: String,
        language: String
    ): NewsResponseDto
}