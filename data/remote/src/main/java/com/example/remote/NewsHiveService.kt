package com.example.remote

import com.example.repositories.dto.NewsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsHiveService {
    @GET("news")
    suspend fun getLatestNews(
        @Query("sort")
        sort:String
    ): Response<NewsResponseDto>
}