package com.example.viewmodel.discover

import com.example.entities.news.LatestNews
import com.example.usecases.GetCategoryNewsUseCase
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.toDiscoverNewsUiState
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
        val categories = listOf("sports", "science", "health", "technology","business","entertainment")
        categories.forEach { category ->
            tryToExecute(
                call = { getCategoryNewsUseCase.getLastCategoryNews(category)

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

    private fun updateUiStateForCategory(category: String, newsList: List<LatestNews>) {
        _state.update { it.copy(isLoading = false, error = null) }
        when (category) {
            "sports" -> _state.update { it.copy(sportsNews = newsList.toDiscoverNewsUiState()) }
            "science" -> _state.update { it.copy(scienceNews = newsList.toDiscoverNewsUiState()) }
            "health" -> _state.update { it.copy(healthNews = newsList.toDiscoverNewsUiState()) }
            "technology" -> _state.update { it.copy(technologyNews = newsList.toDiscoverNewsUiState()) }
            "business" -> _state.update { it.copy(businessNews = newsList.toDiscoverNewsUiState()) }
            "entertainment" -> _state.update { it.copy(entertainmentNews = newsList.toDiscoverNewsUiState()) }
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false,error = throwable.message) }
    }

}
