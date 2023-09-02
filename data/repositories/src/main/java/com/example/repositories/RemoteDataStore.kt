package com.example.repositories

import com.example.repositories.dto.NewsResponseDto

interface RemoteDataStore {
    suspend  fun  getBreakingNews(): NewsResponseDto
}