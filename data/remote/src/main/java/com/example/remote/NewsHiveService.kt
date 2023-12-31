package com.example.remote

import com.example.repositories.remote.NewsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsHiveService {
    @GET("news")
    suspend fun getLatestNews(
        @Query("languages") languages: String,
        @Query("sort") sort: String,
        @Query("offset") offset: Int,
    ): Response<NewsResponseDto>

    @GET("news")
    suspend fun getCategoryNews(
        @Query("categories") categoryName: String,
        @Query("languages") languages: String,
        @Query("sort") sort: String,
        @Query("offset") offset: Int
    ): Response<NewsResponseDto>

    @GET("news")
    suspend fun searchNews(
        @Query("keywords") keyword: String,
        @Query("languages") languages: String,
        @Query("sort") sort: String,
        @Query("offset") offset: Int
    ): Response<NewsResponseDto>

}