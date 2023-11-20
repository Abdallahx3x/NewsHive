package com.example.viewmodel.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.example.entities.news.NewsItemEntity
import com.example.usecases.ManageSearchNewsUseCase
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.encode
import com.example.viewmodel.toSearchNewsUiState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val manageSearchNewsUseCase: ManageSearchNewsUseCase
) : BaseViewModel<SearchUiState, SearchUiEffect>(SearchUiState()), SearchInteraction {

    init {
        collectFlow(state.value.query.debounce(1_000).distinctUntilChanged()) {
            tryToExecute(
                call = { manageSearchNewsUseCase.getSearchNews(it) },
                onSuccess = { onSearchQuerySuccess(it) },
                onError = { onError(it) },
            )
            this
        }

    }

    private fun onSearchQuerySuccess(list: List<NewsItemEntity>) {
        _state.update {
            it.copy(searchItems = list.filter { it.news.imageUrl.isNotEmpty() }
                .toSearchNewsUiState())
        }
    }

    override fun onChangeSearchQuery(list: String) {
        viewModelScope.launch { _state.value.query.emit(list) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickSearchItem(searchItemUiState: SearchItemUiState) {
        val gson = Gson()
        val json = gson.toJson(searchItemUiState.encode())
        sendUiEffect(SearchUiEffect.NavigateToDetails(json))
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

}