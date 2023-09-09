package com.example.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entities.news.ClassifiedNews
import com.example.entities.news.LatestNews
import com.example.usecases.GetBreakingNewsUseCase
import com.example.usecases.GetRecommendationNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
   private val getBreakingNewsUseCase: GetBreakingNewsUseCase,
    private val getRecommendationNewsUseCase: GetRecommendationNewsUseCase
):ViewModel() {
    private val _state= MutableStateFlow(HomeUiState())
    val state=_state.asStateFlow()
    init {
        viewModelScope.launch {
            getBreakingNews( getBreakingNewsUseCase.invoke())
           getRecommendationNews(getRecommendationNewsUseCase.invoke())

        }
    }

    private fun getBreakingNews(news:List<LatestNews>){
        _state.update { it.copy(breakingNewsUiState = news.toUiState()) }
    }
    private fun getRecommendationNews(news:List<LatestNews>){
        _state.update { it.copy(recommendedNewsUiState = news.toRecommendationUiState()) }
    }

}
