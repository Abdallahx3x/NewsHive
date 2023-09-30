package com.example.viewmodel.home

import com.example.entities.news.NewsItemEntity
import com.example.usecases.GetBreakingNewsUseCase
import com.example.usecases.GetRecommendationNewsUseCase
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.toBreakingNewsUiState
import com.example.viewmodel.toEncodeNewsUiState
import com.example.viewmodel.toNewsItemUiState
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
        getBreakingNews()
        getRecommendationNews()
    }


    override fun onClickViewAll() {
        TODO("Not yet implemented")
    }

    override fun onClickBreakingNewsItem(
        title: String,
        content: String,
        imageUrl: String,
        url: String,
        breakingNewsUiState: NewsUiState,
    ) {

       val gson= Gson()
        val json=gson.toJson(breakingNewsUiState.toEncodeNewsUiState())
        sendUiEffect(HomeUiEffect.NavigateToDetails(json))
    }

    override fun onClickRecommendedNewsItem() {
        TODO("Not yet implemented")
    }


    private fun getBreakingNews() {
        _state.update { it.copy(isLoading = true, error = null) }
        tryToExecute(call = { getBreakingNewsUseCase.invoke() },
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
                breakingNewsUiState = news.toBreakingNewsUiState()
            )
        }
    }

    private fun getRecommendationNews() {
        tryToExecute(call = { getRecommendationNewsUseCase.invoke() },
            onSuccess = { newList ->
                newList?.let { onGetRecommendationNews(newList) }
            },
            onError = { throwable ->
                onError(throwable)
            }
        )
    }

    private fun onGetRecommendationNews(news: List<NewsItemEntity>) {
        _state.update { it.copy(recommendedNewsUiState = news.toNewsItemUiState()) }
    }


    private fun onError(throwable: Throwable) {

        _state.update { it.copy(isLoading = false, error = throwable.message) }
    }

}

//
//val fakeNewsData = listOf(
//    LatestNews(
//        title = "Mmooesttes123Aboood test123Aboood test123Abooodtest123Aboood",
//        imageUrl = "https://egyptianstreets.com/wp-content/uploads/2022/10/GettyImages-1243921482.v1.jpg",
//        category = "heell", publishedAt = ""
//    ),
//    LatestNews(
//        title = "Mmooesttes123Aboood test123Aboood test123Abooodtest123Aboood",
//        imageUrl = "https://sport.ec.europa.eu/sites/default/files/styles/eac_ratio_16_9_xl/public/sport-active-part-erasmus-plus-crop.jpg?h=5dabf909&itok=JM-JNmjy",
//        category = "heell", publishedAt = ""
//    ),
//    LatestNews(
//        title = "Mmooesttes test123Aboood test123Abooodtest123Aboood",
//        imageUrl = "https://egyptianstreets.com/wp-content/uploads/2022/10/GettyImages-1243921482.v1.jpg",
//        category = "heell", publishedAt = ""
//    ),
//    LatestNews(
//        title = "Mmooesttes test123Aboood test123Abooodtest123Aboood",
//        imageUrl = "https://sport.ec.europa.eu/sites/default/files/styles/eac_ratio_16_9_xl/public/onea_gepa-pictures_0.jpg?h=59fa23e0&itok=ML7kB0yM",
//        category = "heell", publishedAt = ""
//    ),
//    LatestNews(
//        title = "Mmooesttes test123Aboood test123Abooodtest123Aboood",
//        imageUrl = "https://egyptianstreets.com/wp-content/uploads/2022/10/GettyImages-1243921482.v1.jpg",
//        category = "heell", publishedAt = ""
//    )
//)