package com.example.viewmodel.details

import androidx.lifecycle.SavedStateHandle

class DetailsArgs(savedStateHandle: SavedStateHandle) {
    val newsItem: String = checkNotNull(savedStateHandle[NEWS_ITEM])


    companion object {
        const val NEWS_ITEM = "newsItem"
    }
}