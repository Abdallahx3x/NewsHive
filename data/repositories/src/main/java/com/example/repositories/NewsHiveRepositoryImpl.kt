package com.example.repositories

import com.example.entities.news.LatestNews
import com.example.repositories.mapper.toLatestNews
import com.example.usecases.NewsHiveRepository
import javax.inject.Inject

class NewsHiveRepositoryImpl @Inject constructor(
    private val remoteDataStore: RemoteDataStore
) : NewsHiveRepository {
    override suspend fun getLatestNews(sort:String): List<LatestNews>{
        return remoteDataStore.getLatestNews(sort).data!!.map { it!!.toLatestNews() }
    }

}