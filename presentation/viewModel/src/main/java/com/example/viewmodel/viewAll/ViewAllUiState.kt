package com.example.viewmodel.viewAll

import androidx.paging.PagingData
import com.example.viewmodel.base.BaseUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class ViewAllUiState(
    val viewAllItemsUiState: Flow<PagingData<ViewAllItemUiState>> = flow { },
    val isLoading: Boolean = false,
    val error: String? = null
)

data class ViewAllItemUiState(
    override val title: String = "",
    override val content: String = "",
    override val imageUrl: String = "",
    override val url: String = "",
    override val publishedAt: String = "",
    val category: String = ""
) : BaseUiState()

fun ViewAllUiState.showError() = !isLoading && error != null
fun ViewAllUiState.showLoading() = isLoading && error == null
fun ViewAllUiState.showContent() = !isLoading && error == null