package com.example.repositories.remote


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

    suspend fun searchNews(
        keyword: String,
        language: String,
        sort: String
    ): NewsResponseDto
}