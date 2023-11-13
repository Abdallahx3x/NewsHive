package com.example.repositories

import com.example.entities.news.NewsItemEntity
import com.example.repositories.local.LocalDataStore
import com.example.repositories.mapper.toNewsItemEntities
import com.example.repositories.mapper.toNewsItemEntity
import com.example.repositories.mapper.toNewsLocalDto
import com.example.repositories.remote.RemoteDataStore
import com.example.usecases.NewsHiveRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsHiveRepositoryImpl @Inject constructor(
    private val remoteDataStore: RemoteDataStore,
    private val localDataStore: LocalDataStore
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

    override suspend fun saveNewsForLater(newsItemEntity: NewsItemEntity) {
        localDataStore.insertOrUpdateNews(newsItemEntity.toNewsLocalDto())
    }

    override suspend fun getSavedNews(): Flow<List<NewsItemEntity>> {
        return localDataStore.getAllSavedNews().map { it.toNewsItemEntities() }
    }

    override suspend fun deleteSavedNews(title: String) {
        return localDataStore.deleteSavedNewsByTitle(title)
    }

}