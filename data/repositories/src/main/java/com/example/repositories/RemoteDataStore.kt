package com.example.repositories

import com.example.repositories.dto.NewsResponseDto

interface RemoteDataStore {
    suspend  fun  getLatestNews(sort:String): NewsResponseDto
}