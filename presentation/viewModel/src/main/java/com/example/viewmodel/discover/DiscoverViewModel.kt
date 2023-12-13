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
        onRefreshData()
    }

    private fun fetchCategoryNews() {
        _state.update { it.copy(isLoading = true, error = null) }
        state.value.categories.forEach { category ->
            tryToExecute(
                call = {
                    getCategoryNewsUseCase.getLastCategoryNews(category)
                },
                onSuccess = { newList ->
                    newList?.let { updateUiStateForCategory(category, newList) }
                },
                onError = { throwable -> onError(throwable) }
            )
        }
    }

    override fun onRefreshData() {
        fetchCategoryNews()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickCategoryItem(categoryNewsUiState: CategoryNewsUiState) {
        val gson = Gson()
        val json = gson.toJson(categoryNewsUiState.encode())
        sendUiEffect(DiscoverUiEffect.NavigateToDetails(json))
    }

    private fun updateUiStateForCategory(category: String, newsList: List<NewsItemEntity>) {
        _state.update { it.copy(isLoading = false, error = null) }
        val list = newsList.filter { it.news.imageUrl.isNotEmpty() }.toCategoryNewsUiState()
        when (category) {
            "sports" -> _state.update { it.copy(sportsNews = list) }
            "science" -> _state.update { it.copy(scienceNews = list) }
            "health" -> _state.update { it.copy(healthNews = list) }
            "technology" -> _state.update { it.copy(technologyNews = list) }
            "business" -> _state.update { it.copy(businessNews = list) }
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }
}
