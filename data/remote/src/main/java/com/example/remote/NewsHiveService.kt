package com.example.remote

import com.example.repositories.remote.NewsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsHiveService {
    @GET("news")
    suspend fun getLatestNews(
        @Query("sort") sort: String,
        @Query("countries") countries: String,
        @Query("languages") languages: String
    ): Response<NewsResponseDto>

    @GET("news")
    suspend fun getCategoryNews(
        @Query("categories") categoryName: String,
        @Query("countries") countries: String,
        @Query("languages") languages: String,
        @Query("sort") sort: String
    ): Response<NewsResponseDto>

    @GET("news")
    suspend fun searchNews(
        @Query("keywords") keyword: String,
        @Query("languages") languages: String,
        @Query("sort") sort: String
    ): Response<NewsResponseDto>

}