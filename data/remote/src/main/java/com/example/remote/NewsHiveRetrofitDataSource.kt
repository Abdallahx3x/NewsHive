package com.example.remote

import com.example.remote.util.wrapApiCall
import com.example.repositories.RemoteDataStore
import com.example.repositories.dto.NewsResponseDto
import javax.inject.Inject

class NewsHiveRetrofitDataSource @Inject constructor(
    private val newsHiveService: NewsHiveService
) : RemoteDataStore {
    override suspend fun getLatestNews(
        sort: String,
        countries: String,
        language: String
    ): NewsResponseDto {
        return wrapApiCall { newsHiveService.getLatestNews(sort, countries, language) }
    }

    override suspend fun getCategoryNews(
        categoryName: String,
        sort: String,
        countries: String,
        language: String
    ): NewsResponseDto {
        return wrapApiCall {
            newsHiveService.getCategoryNews(categoryName, countries, language, sort)
        }
    }
}

