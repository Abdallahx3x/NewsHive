package com.example.remote

import com.example.remote.util.wrapApiCall
import com.example.repositories.remote.RemoteDataStore
import com.example.repositories.remote.NewsResponseDto
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

    override suspend fun searchNews(
        keyword: String,
        language: String,
        sort: String
    ): NewsResponseDto {
        return wrapApiCall { newsHiveService.searchNews(keyword, language, sort) }
    }
}

