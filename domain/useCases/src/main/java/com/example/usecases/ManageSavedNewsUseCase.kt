package com.example.usecases

import com.example.entities.news.NewsItemEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ManageSavedNewsUseCase @Inject constructor(
    private val newsHiveRepository: NewsHiveRepository
) {
    suspend fun saveNewsForLater(newsItemEntity: NewsItemEntity) {
        newsHiveRepository.saveNewsForLater(newsItemEntity)
    }

    suspend fun getSavedNews(): Flow<List<NewsItemEntity>> {
        return newsHiveRepository.getSavedNews()
    }

    suspend fun deleteSavedNews(title: String) {
        newsHiveRepository.deleteSavedNews(title)
    }
}