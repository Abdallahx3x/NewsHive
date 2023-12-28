package com.example.usecases

import androidx.paging.filter
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class GetRecommendationNewsUseCase @Inject constructor(
    private val newsHiveRepository: NewsHiveRepository
) {
    suspend fun invoke() = newsHiveRepository
        .getLatestNews(
            sort = SORT,
            language = LANGUAGE
        ).drop(5).take(4).distinctBy { it.news.title }


    companion object {
        const val SORT = "published_desc"
        const val LANGUAGE = "en"
    }
}