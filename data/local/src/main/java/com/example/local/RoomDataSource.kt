package com.example.local

import com.example.repositories.local.LocalDataStore
import com.example.repositories.local.NewsLocalDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomDataSource @Inject constructor(
   private val newsHiveDao: NewsHiveDao
) : LocalDataStore {
    override suspend fun insertOrUpdateNews(newsLocalDto: NewsLocalDto) {
        newsHiveDao.insertOrUpdateNews(newsLocalDto)
    }

    override suspend fun getAllSavedNews(): Flow<List<NewsLocalDto>> {
        return newsHiveDao.getAllSavedNews()
    }

    override suspend fun deleteSavedNewsByTitle(title: String) {
        return newsHiveDao.deleteSavedNewsByTitle(title)
    }
}