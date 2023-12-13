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
import kotlinx.coroutines.FlowPreview
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
        onRefreshData()
    }

    @OptIn(FlowPreview::class)
    private fun getData() {
        collectFlow(state.value.query.debounce(1_000).distinctUntilChanged()) {
            _state.update { it.copy(isLoading = true, error = null, empty = false) }
            tryToExecute(
                call = { manageSearchNewsUseCase.getSearchNews(it) },
                onSuccess = { onSearchQuerySuccess(it) },
                onError = { onError(it) },
            )
            this
        }
    }

    override fun onRefreshData() {
        getData()
    }

    private fun onSearchQuerySuccess(list: List<NewsItemEntity>) {
        if (list.isNotEmpty()) {
            _state.update {
                it.copy(
                    isLoading = false,
                    searchItems = list.filter { newsItem -> newsItem.news.imageUrl.isNotEmpty() }
                        .toSearchNewsUiState()
                )
            }
        } else {
            _state.update { it.copy(isLoading = false, empty = true) }
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
