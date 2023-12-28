package com.example.usecases

import javax.inject.Inject

class GetBreakingNewsUseCase @Inject constructor(
    private val newsHiveRepository: NewsHiveRepository
) {
    suspend fun invoke() = newsHiveRepository
        .getLatestNews(
            sort = SORT,
            language = LANGUAGE
        ).take(5).distinctBy { it.news.title }

    companion object {
        const val SORT = "published_desc"
        const val LANGUAGE = "en"
    }
}