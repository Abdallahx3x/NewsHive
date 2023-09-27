package com.example.viewmodel.details

import androidx.lifecycle.SavedStateHandle
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.home.NewsUiState
import com.example.viewmodel.toDecodeNewsUiState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailsUiState, DetailsUiEffect>(DetailsUiState()), DetailsInteraction {

    private val detailsArgs = DetailsArgs(savedStateHandle)

    init {
        val gson= Gson()
        val json =detailsArgs.newsItem
        val newsItem=gson.fromJson(json,NewsUiState::class.java)
        val decodeNewsItem=newsItem.toDecodeNewsUiState()
        _state.update {
            it.copy(
                title = decodeNewsItem.title,
                content =decodeNewsItem.content,
                imageUrl = decodeNewsItem.imageUrl,
                url = decodeNewsItem.url,
            )
        }
    }

    override fun onClickSaveIcon() {
        TODO("Not yet implemented")
    }

    override fun onClickBackArrowIcon() {
        TODO("Not yet implemented")
    }

}
