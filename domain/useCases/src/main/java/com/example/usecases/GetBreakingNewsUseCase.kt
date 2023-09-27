package com.example.usecases

import javax.inject.Inject

class GetBreakingNewsUseCase @Inject constructor(
    private val newsHiveRepository: NewsHiveRepository
) {
    suspend fun invoke() = newsHiveRepository
        .getLatestNews("published_desc", "-us", "en")?.take(5)
}