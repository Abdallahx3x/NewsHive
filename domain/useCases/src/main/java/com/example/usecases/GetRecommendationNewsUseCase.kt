package com.example.usecases

import javax.inject.Inject

class GetRecommendationNewsUseCase @Inject constructor (
    private val newsHiveRepository: NewsHiveRepository
) {
    suspend fun invoke() = newsHiveRepository.getLatestNews("published_desc").drop(5).take(4)
}