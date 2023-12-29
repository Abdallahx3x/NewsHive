package com.example.viewmodel.discover

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.example.entities.news.NewsItemEntity
import com.example.usecases.ManageCategoryNewsUseCase
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.base.StringsResource
import com.example.viewmodel.mapper.encode
import com.example.viewmodel.mapper.toCategoryNewsUiState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val getCategoryNewsUseCase: ManageCategoryNewsUseCase,
    private val stringsResource: StringsResource
) : BaseViewModel<DiscoverUiState, DiscoverUiEffect>(DiscoverUiState()), DiscoverInteraction {

    init {
        onRefreshData()
    }

    private fun fetchCategoryNews() {
        _state.update { it.copy(isLoading = true, error = null) }
        state.value.categories.forEach { category ->
            tryToExecutePaging(
                call = {
                    getCategoryNewsUseCase.getLastCategoryNews(category)
                },
                onSuccess = { newList ->
                    updateUiStateForCategory(category, newList)
                },
                onError = { onError(it) }
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

    private fun updateUiStateForCategory(
        category: String,
        categoryNewsData: PagingData<NewsItemEntity>
    ) {
        _state.update { it.copy(isLoading = false, error = null) }
        val filteredList = categoryNewsData.filter { it.news.imageUrl.isNotEmpty() }
        val uiStateList = flowOf(filteredList.map { it.toCategoryNewsUiState() })
        when (category) {
            stringsResource.sports -> _state.update { it.copy(sportsNews = uiStateList) }
            stringsResource.science -> _state.update { it.copy(scienceNews = uiStateList) }
            stringsResource.health -> _state.update { it.copy(healthNews = uiStateList) }
            stringsResource.technology -> _state.update { it.copy(technologyNews = uiStateList) }
            stringsResource.business -> _state.update { it.copy(businessNews = uiStateList) }
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }
}
