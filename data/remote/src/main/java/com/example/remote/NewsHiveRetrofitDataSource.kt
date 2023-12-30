package com.example.remote

import com.example.remote.util.wrapApiCall
import com.example.repositories.remote.NewsResponseDto
import com.example.repositories.remote.RemoteDataStore
import javax.inject.Inject

class NewsHiveRetrofitDataSource @Inject constructor(
    private val newsHiveService: NewsHiveService
) : RemoteDataStore {
    override suspend fun getLatestNews(
        sort: String,
        language: String,
        offset: Int
    ): NewsResponseDto {
        return wrapApiCall {
            newsHiveService.getLatestNews(
                languages = language,
                sort = sort,
                offset = offset
            )
        }
    }

    override suspend fun getCategoryNews(
        categoryName: String,
        sort: String,
        language: String,
        offset: Int
    ): NewsResponseDto {
        return wrapApiCall {
            newsHiveService.getCategoryNews(
                categoryName = categoryName,
                languages = language,
                sort = sort,
                offset = offset
            )
        }
    }

    override suspend fun searchNews(
        keyword: String,
        language: String,
        sort: String,
        offset: Int
    ): NewsResponseDto {
        return wrapApiCall {
            newsHiveService.searchNews(
                keyword = keyword,
                languages = language,
                sort = sort,
                offset = offset
            )
        }
    }

}

