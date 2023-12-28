package com.example.repositories.remote


interface RemoteDataStore {
    suspend fun getLatestNews(
        sort: String,
        language: String,
        offset:Int
    ): NewsResponseDto

    suspend fun getCategoryNews(
        categoryName: String,
        sort: String,
        language: String,
        offset:Int
    ): NewsResponseDto

    suspend fun searchNews(
        keyword: String,
        language: String,
        sort: String,
        offset:Int
    ): NewsResponseDto
}