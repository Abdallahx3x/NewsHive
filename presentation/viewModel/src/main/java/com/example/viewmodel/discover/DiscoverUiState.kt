package com.example.viewmodel.discover

import androidx.paging.PagingData
import com.example.viewmodel.base.BaseUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class DiscoverUiState(
    val categories: List<String> = listOf("sports", "technology", "health", "science", "business"),
    val sportsNews: Flow<PagingData<CategoryNewsUiState>> = flow { },
    val scienceNews: Flow<PagingData<CategoryNewsUiState>> = flow { },
    val healthNews: Flow<PagingData<CategoryNewsUiState>> = flow { },
    val technologyNews: Flow<PagingData<CategoryNewsUiState>> = flow { },
    val businessNews: Flow<PagingData<CategoryNewsUiState>> = flow { },
    val isLoading: Boolean = false,
    val error: String? = null
)

data class CategoryNewsUiState(
    override val title: String = "",
    override val imageUrl: String = "",
    override val content: String = "",
    override val url: String = "",
    override val publishedAt: String = "",
    override val category: String = ""
) : BaseUiState()

data class Category(
    val name: String,
    val list: Flow<PagingData<CategoryNewsUiState>>
)

fun DiscoverUiState.categoryNews() = mutableListOf(
    Category("Sports", sportsNews),
    Category("Technology", technologyNews),
    Category("Health", healthNews),
    Category("Science", scienceNews),
    Category("Business", businessNews),
)

fun DiscoverUiState.showError() = !isLoading && error != null
fun DiscoverUiState.showLoading() = isLoading && error == null
fun DiscoverUiState.showContent() = !isLoading && error == null
