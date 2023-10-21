package com.example.viewmodel.discover

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.entities.news.NewsItemEntity
import com.example.usecases.GetCategoryNewsUseCase
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.encode
import com.example.viewmodel.toCategoryNewsUiState
import com.google.gson.Gson
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickCategoryItem(categoryNewsUiState: CategoryNewsUiState) {
        val gson = Gson()
        val json = gson.toJson(categoryNewsUiState.encode())
        sendUiEffect(DiscoverUiEffect.NavigateToDetails(json))
    }

    override fun updatePageIndex(pageIndex: Int) {
        _state.update { it.copy(pageIndex = pageIndex) }
    }

     fun fetchCategoryNews() {
        _state.update { it.copy(isLoading = true, error = null) }
        state.value.categories.forEach { category ->
            tryToExecute(
                call = {
                    getCategoryNewsUseCase.getLastCategoryNews(category)?.filter { it.news.imageUrl.isNotEmpty() }
                },
                onSuccess = { newList ->
                    newList?.let { updateUiStateForCategory(category, it) }
                },
                onError = { throwable ->
                    onError(throwable)
                }
            )
        }
    }

    private fun updateUiStateForCategory(category: String, newsList: List<NewsItemEntity>) {
        _state.update { it.copy(isLoading = false, error = null) }
        when (category) {
            "sports" -> _state.update { it.copy(sportsNews = newsList.toCategoryNewsUiState()) }
            "science" -> _state.update { it.copy(scienceNews = newsList.toCategoryNewsUiState()) }
            "health" -> _state.update { it.copy(healthNews = newsList.toCategoryNewsUiState()) }
            "technology" -> _state.update { it.copy(technologyNews = newsList.toCategoryNewsUiState()) }
            "business" -> _state.update { it.copy(businessNews = newsList.toCategoryNewsUiState()) }
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

}
