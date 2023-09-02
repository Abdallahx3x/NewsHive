package com.example.remote

import com.example.repositories.dto.NewsResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface NewsHiveService {
    @GET("news")
    suspend fun getBreakingNews(
    ): Response<NewsResponseDto>
}