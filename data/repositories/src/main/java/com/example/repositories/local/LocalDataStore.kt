package com.example.repositories.local

import kotlinx.coroutines.flow.Flow

interface LocalDataStore {
    suspend fun insertOrUpdateNews(newsLocalDto: NewsLocalDto)
    suspend fun getAllSavedNews(): Flow<List<NewsLocalDto>>
    suspend fun deleteSavedNewsByTitle(title: String)
}