package com.example.repositories

import com.example.entities.news.BreakingNews
import com.example.repositories.mapper.toBreakingNews
import com.example.usecases.NewsHiveRepository
import javax.inject.Inject

class NewsHiveRepositoryImplementation @Inject constructor(
    private val remoteDataStore: RemoteDataStore
) : NewsHiveRepository {
    override suspend fun getBreakingNews(): List<BreakingNews?>? {
        return remoteDataStore.getBreakingNews().data?.map { it?.toBreakingNews() }
    }

}