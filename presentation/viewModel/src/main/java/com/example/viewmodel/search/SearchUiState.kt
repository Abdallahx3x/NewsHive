package com.example.viewmodel.search

import androidx.paging.PagingData
import com.example.viewmodel.base.BaseUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

data class SearchUiState(
    val query: MutableStateFlow<String> = MutableStateFlow(""),
    val searchItems: Flow<PagingData<SearchItemUiState>> = flow { },
    val isLoading: Boolean = false,
    val error: String? = null
)

data class SearchItemUiState(
    override val title: String = "",
    override val content: String = "",
    override val imageUrl: String = "",
    override val url: String = "",
    override val publishedAt: String = "",
    val category: String = ""
) : BaseUiState()

fun SearchUiState.showError() = !isLoading && error != null
fun SearchUiState.showLoading() = isLoading && error == null
fun SearchUiState.showContent() = !isLoading && error == null
fun SearchUiState.showEmpty() = !isLoading && error == null


