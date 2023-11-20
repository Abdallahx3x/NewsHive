package com.example.viewmodel.search

interface SearchInteraction {
    fun onChangeSearchQuery(query: String)
    fun onClickSearchItem(searchItemUiState: SearchItemUiState)
}