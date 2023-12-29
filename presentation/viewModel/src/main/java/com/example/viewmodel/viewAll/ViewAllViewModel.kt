package com.example.viewmodel.viewAll

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.example.entities.news.NewsItemEntity
import com.example.usecases.ManageRecommendationNewsUseCase
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.mapper.encode
import com.example.viewmodel.mapper.toViewAllItemUiState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ViewAllViewModel @Inject constructor(
    private val getRecommendationNewsUseCase: ManageRecommendationNewsUseCase
) : BaseViewModel<ViewAllUiState, ViewAllUiEffect>(ViewAllUiState()), ViewAllInteraction {

    init {
        onRefreshData()
    }

    private fun fetchViewAllNews() {
        _state.update { it.copy(isLoading = true, error = null) }
        tryToExecutePaging(
            call = {
                getRecommendationNewsUseCase.getAllRecommendationNews()
            },
            onSuccess = { newList ->
                onGetViewAllNews(newList)
            },
            onError = { onError(it) }
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickViewAllItem(viewAllItemUiState: ViewAllItemUiState) {
        val gson = Gson()
        val json = gson.toJson(viewAllItemUiState.encode())
        sendUiEffect(ViewAllUiEffect.NavigateToDetails(json))
    }

    override fun onRefreshData() {
        fetchViewAllNews()
    }

    override fun onClickBackButton() {
        sendUiEffect(ViewAllUiEffect.NavigateBack)
    }

    private fun onGetViewAllNews(viewAllNewsData: PagingData<NewsItemEntity>) {
        val filteredList = viewAllNewsData.filter { it.news.imageUrl.isNotEmpty() }
        val uiStateList = flowOf(filteredList.map { it.toViewAllItemUiState() })
        _state.update {
            it.copy(
                isLoading = false,
                viewAllItemsUiState =uiStateList ,
                error = null
            )
        }
    }
    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

}