package com.example.viewmodel.discover

import com.example.entities.news.NewsItemEntity
import com.example.usecases.GetCategoryNewsUseCase
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.toNewsItemUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val getCategoryNewsUseCase: GetCategoryNewsUseCase
) : BaseViewModel<DiscoverUiState, DiscoverUiEffect>(DiscoverUiState()), DiscoverInteraction {

    init {
        fetchCategoryNews()
    }

    override fun onClickCategoryItem() {
        TODO("Not yet implemented")
    }

    override fun updatePageIndex(pageIndex: Int) {
        _state.update { it.copy(pageIndex = pageIndex) }
    }

    private fun fetchCategoryNews() {
        _state.update { it.copy(isLoading = true, error = null) }
        val categories =
            listOf("sports", "science", "health", "technology", "business", "entertainment")
        categories.forEach { category ->
            tryToExecute(
                call = {
                    getCategoryNewsUseCase.getLastCategoryNews(category)
                },
                onSuccess = { newList ->
                    updateUiStateForCategory(category, newList)
                },
                onError = { throwable ->
                    onError(throwable)
                }
            )
        }
    }

    private fun updateUiStateForCategory(category: String, newsList: List<NewsItemEntity>?) {
        _state.update { it.copy(isLoading = false, error = null) }
        when (category) {
            "sports" -> _state.update { it.copy(sportsNews = newsList?.toNewsItemUiState()) }
            "science" -> _state.update { it.copy(scienceNews = newsList?.toNewsItemUiState()) }
            "health" -> _state.update { it.copy(healthNews = newsList?.toNewsItemUiState()) }
            "technology" -> _state.update { it.copy(technologyNews = newsList?.toNewsItemUiState()) }
            "business" -> _state.update { it.copy(businessNews = newsList?.toNewsItemUiState()) }
            "entertainment" -> _state.update { it.copy(entertainmentNews = newsList?.toNewsItemUiState()) }
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

}
