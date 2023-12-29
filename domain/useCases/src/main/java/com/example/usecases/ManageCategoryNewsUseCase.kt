package com.example.usecases

import javax.inject.Inject

class ManageCategoryNewsUseCase @Inject constructor(
    private val newsHiveRepository: NewsHiveRepository
) {
    suspend fun getLastCategoryNews(categoryName: String) = newsHiveRepository
        .getCategoryNews(
            categoryName = categoryName,
            sort = SORT,
            language = LANGUAGE
        )

    companion object {
        const val SORT = "published_desc"
        const val LANGUAGE = "en"
    }
}