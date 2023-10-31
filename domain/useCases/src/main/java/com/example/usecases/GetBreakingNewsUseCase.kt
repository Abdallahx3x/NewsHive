package com.example.usecases
import javax.inject.Inject

class GetBreakingNewsUseCase @Inject constructor(
    private val newsHiveRepository: NewsHiveRepository
) {
    suspend fun invoke() = newsHiveRepository
        .getLatestNews("published_desc", "", "en")
        ?.take(5)
        ?.distinctBy { it.news.title }
}