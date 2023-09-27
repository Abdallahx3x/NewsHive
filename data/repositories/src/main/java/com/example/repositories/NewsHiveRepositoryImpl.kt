package com.example.repositories

import com.example.entities.news.NewsItemEntity
import com.example.repositories.mapper.toNewsItemEntity
import com.example.usecases.NewsHiveRepository
import javax.inject.Inject

class NewsHiveRepositoryImpl @Inject constructor(
    private val remoteDataStore: RemoteDataStore
) : NewsHiveRepository {
    override suspend fun getLatestNews(
        sort: String,
        countries: String,
        language: String
    ): List<NewsItemEntity>? {
        return remoteDataStore.getLatestNews(
            sort, countries, language
        ).data?.map { it.toNewsItemEntity() }
    }

    override suspend fun getCategoryNews(
        categoryName: String,
        sort: String,
        countries: String,
        language: String
    ): List<NewsItemEntity>? {
        return remoteDataStore.getCategoryNews(
            categoryName, sort, countries, language
        ).data?.map { it.toNewsItemEntity() }
    }

}