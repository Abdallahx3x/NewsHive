package com.example.usecases

import androidx.paging.map
import kotlinx.coroutines.flow.distinctUntilChangedBy
import javax.inject.Inject

class ManageSearchNewsUseCase @Inject constructor(
    private val newsHiveRepository: NewsHiveRepository
) {
    suspend fun getSearchNews(keyword: String) = newsHiveRepository
        .searchNews(keyword = keyword, language = LANGUAGE, sort = SORT)
        .distinctUntilChangedBy { it.map { list -> list.news.title } }

    companion object {
        const val SORT = "published_desc"
        const val LANGUAGE = "en"
    }
}