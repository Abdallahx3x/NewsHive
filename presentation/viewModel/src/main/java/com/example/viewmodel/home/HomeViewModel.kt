package com.example.viewmodel.home

import android.annotation.SuppressLint
import com.example.entities.news.NewsItemEntity
import com.example.usecases.GetBreakingNewsUseCase
import com.example.usecases.GetRecommendationNewsUseCase
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.encode
import com.example.viewmodel.toBreakingNewsUiState
import com.example.viewmodel.toRecommendedNewsUiState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBreakingNewsUseCase: GetBreakingNewsUseCase,
    private val getRecommendationNewsUseCase: GetRecommendationNewsUseCase
) : BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteraction {
    init {
        onRefreshData()
    }

    override fun onRefreshData() {
        getBreakingNews()
        getRecommendationNews()
    }

    override fun onClickViewAll() {
        TODO("Not yet implemented")
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
        tryToExecute(call = {
            getBreakingNewsUseCase.invoke()?.filter { it.news.imageUrl.isNotEmpty() }
        },
            onSuccess = { newList ->
                newList?.let { onGetBreakingNewsSuccess(it) }
            },
            onError = { throwable ->
                onError(throwable)
            }
        )
    }

    private fun onGetBreakingNewsSuccess(news: List<NewsItemEntity>) {
        _state.update {
            it.copy(
                isLoading = false,
                breakingNewsUiState = news.filter { newsItem -> newsItem.news.imageUrl.isNotEmpty() }
                    .toBreakingNewsUiState()
            )
        }
    }

    private fun getRecommendationNews() {
        _state.update { it.copy(isLoading = true, error = null) }
        tryToExecute(call = {
            getRecommendationNewsUseCase.invoke()
        },
            onSuccess = { newList ->
                newList?.let { onGetRecommendationNews(newList) }
            },
            onError = { throwable ->
                onError(throwable)
            }
        )
    }

    private fun onGetRecommendationNews(news: List<NewsItemEntity>) {
        _state.update {
            it.copy(
                isLoading = false,
                recommendedNewsUiState = news.filter { newsItem -> newsItem.news.imageUrl.isNotEmpty() }
                    .toRecommendedNewsUiState()

            )
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

}