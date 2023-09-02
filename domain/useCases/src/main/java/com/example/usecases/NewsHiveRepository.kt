package com.example.usecases

import com.example.entities.news.BreakingNews

interface NewsHiveRepository {
    suspend fun getBreakingNews(): List<BreakingNews?>?
}