package com.example.viewmodel.home

import com.example.viewmodel.base.BaseUiState


data class HomeUiState(
    val breakingNewsUiState: List<BreakingNewsUiState> = emptyList(),
    val recommendedNewsUiState: List<RecommendedNewsUiState> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class BreakingNewsUiState(
    override val title: String = "",
    override val content: String = "",
    override val imageUrl: String = "",
    override val url: String = "",
) : BaseUiState()


data class RecommendedNewsUiState(
    override val title: String = "",
    override val content: String = "",
    override val imageUrl: String = "",
    override val url: String = "",
    override val publishedAt: String = "",
    override val category: String = ""
) : BaseUiState()

fun HomeUiState.showError() = !isLoading && error != null
fun HomeUiState.showLoading() = isLoading && error == null
fun HomeUiState.showContent() = !isLoading && error == null

