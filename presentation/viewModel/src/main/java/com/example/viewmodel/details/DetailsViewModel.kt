package com.example.viewmodel.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import com.example.viewmodel.base.BaseUiState
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.decode
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailsUiState, DetailsUiEffect>(DetailsUiState()), DetailsInteraction {

    private val detailsArgs = DetailsArgs(savedStateHandle)

    init {
        val gson= Gson()
        val json =detailsArgs.newsItem
        val newsItem=gson.fromJson(json,BaseUiState::class.java)
        val decodeNewsItem=newsItem.decode()
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
