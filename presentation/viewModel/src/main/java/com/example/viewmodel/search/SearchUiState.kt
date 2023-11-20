package com.example.viewmodel.search

import com.example.viewmodel.base.BaseUiState
import kotlinx.coroutines.flow.MutableStateFlow

data class SearchUiState(
    val query: MutableStateFlow<String> = MutableStateFlow(""),
    val searchItems:List<SearchItemUiState> = emptyList(),
    val isLoading:Boolean = false,
    val error: String? = null
)

data class SearchItemUiState(
    override val title: String = "",
    override val content: String = "",
    override val imageUrl: String = "",
    override val url: String = "",
    val category: String = "",
    val publishedAt: String = ""
) : BaseUiState()
