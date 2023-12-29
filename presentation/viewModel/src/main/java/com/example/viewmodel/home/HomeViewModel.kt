package com.example.viewmodel.home

import android.annotation.SuppressLint
import com.example.entities.news.NewsItemEntity
import com.example.usecases.ManageBreakingNewsUseCase
import com.example.usecases.ManageRecommendationNewsUseCase
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.mapper.encode
import com.example.viewmodel.mapper.toBreakingNewsUiState
import com.example.viewmodel.mapper.toRecommendedNewsUiState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBreakingNewsUseCase: ManageBreakingNewsUseCase,
    private val getRecommendationNewsUseCase: ManageRecommendationNewsUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteraction {
    init {
        onRefreshData()
    }

    override fun onRefreshData() {
        getBreakingNews()
        getRecommendationNews()
    }

    override fun onClickViewAll() {
        sendUiEffect(HomeUiEffect.NavigateToViewALl)
    }

    @SuppressLint("NewApi")
    override fun onClickBreakingNewsItem(breakingNewsUiState: BreakingNewsUiState) {
        val gson = Gson()
        val json = gson.toJson(breakingNewsUiState.encode())
        sendUiEffect(HomeUiEffect.NavigateToDetails(json))
    }

    @SuppressLint("NewApi")
    override fun onClickRecommendedNewsItem(recommendedNewsUiState: RecommendedNewsUiState) {
        val gson = Gson()
        val json = gson.toJson(recommendedNewsUiState.encode())
        sendUiEffect(HomeUiEffect.NavigateToDetails(json))
    }


    private fun getBreakingNews() {
        _state.update { it.copy(isLoading = true, error = null) }
        tryToExecute(
            call = getBreakingNewsUseCase::invoke,
            onSuccess = ::onGetBreakingNewsSuccess,
            onError = ::onError
        )
    }

    private fun onGetBreakingNewsSuccess(breakingNewsData: List<NewsItemEntity>) {
        _state.update {
            it.copy(
                isLoading = false,
                breakingNewsUiState = breakingNewsData.toBreakingNewsUiState()
            )
        }
    }

    private fun getRecommendationNews() {
        _state.update { it.copy(isLoading = true, error = null) }
        tryToExecute(
            call = getRecommendationNewsUseCase::getRecommendationNews,
            onSuccess = ::onGetRecommendationNews,
            onError = ::onError
        )
    }

    private fun onGetRecommendationNews(recommendationNewsData: List<NewsItemEntity>) {
        _state.update {
            it.copy(
                isLoading = false,
                recommendedNewsUiState = recommendationNewsData.toRecommendedNewsUiState()
            )
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

}