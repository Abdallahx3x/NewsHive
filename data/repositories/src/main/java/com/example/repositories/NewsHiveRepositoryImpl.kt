package com.example.repositories

import com.example.entities.news.LatestNews
import com.example.repositories.mapper.toLatestNews
import com.example.usecases.NewsHiveRepository
import javax.inject.Inject

class NewsHiveRepositoryImpl @Inject constructor(
    private val remoteDataStore: RemoteDataStore
) : NewsHiveRepository {
    override suspend fun getLatestNews(
        sort: String,
        countries: String,
        language: String
    ): List<LatestNews> {
        return remoteDataStore.getLatestNews(
            sort, countries, language
        ).data!!.map { it!!.toLatestNews() }
    }

    override suspend fun getCategoryNews(
        categoryName: String,
        sort: String,
        countries: String,
        language: String
    ): List<LatestNews> {
        return remoteDataStore.getCategoryNews(
            categoryName, sort, countries, language
        ).data!!.map { it!!.toLatestNews() }
    }

}