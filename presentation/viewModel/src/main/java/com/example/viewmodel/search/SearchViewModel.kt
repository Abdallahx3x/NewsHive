package com.example.viewmodel.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
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
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEmpty
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
        collectFlow(state.value.query.debounce(1_000).distinctUntilChanged()) {query->
            _state.update { it.copy(isLoading = true, error = null) }
            if(query.isNotBlank()){
                tryToExecutePaging(
                    call = { manageSearchNewsUseCase.getSearchNews(query) },
                    onSuccess = { onSearchQuerySuccess(it) },
                    onError = { onError(it) },
                )
            }else{
                _state.update { it.copy(isLoading = false, error = null) }
            }
            this
        }
    }

    override fun onRefreshData() {
        getData()
    }

    private fun onSearchQuerySuccess(searchNewsData: PagingData<NewsItemEntity>) {
        val filteredList = searchNewsData.filter { it.news.imageUrl.isNotEmpty() }
        val uiStateList = flowOf(filteredList.map { it.toSearchNewsUiState() })
        _state.update { it.copy(isLoading = false, searchItems = uiStateList) }
    }

    override fun onChangeSearchQuery(query: String) {
        viewModelScope.launch { _state.value.query.emit(query) }
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
