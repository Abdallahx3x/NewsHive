package com.example.usecases

import com.example.entities.news.LatestNews

interface NewsHiveRepository {
    suspend fun getLatestNews(sort:String): List<LatestNews>
}