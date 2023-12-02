package com.example.viewmodel.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import com.example.usecases.ManageSavedNewsUseCase
import com.example.viewmodel.base.BaseUiState
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.decode
import com.example.viewmodel.toNewsItemEntity
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val manageSaveLaterUseCase: ManageSavedNewsUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailsUiState, DetailsUiEffect>(DetailsUiState()), DetailsInteraction {
    private val detailsArgs = DetailsArgs(savedStateHandle)

    init {
        initializeDetails()
    }

    private fun initializeDetails() {
        val gson = Gson()
        val json = detailsArgs.newsItem
        val newsItem = gson.fromJson(json, BaseUiState::class.java)
        val decodeNewsItem = newsItem.decode()
        _state.update {
            it.copy(
                title = decodeNewsItem.title,
                content = decodeNewsItem.content,
                imageUrl = decodeNewsItem.imageUrl,
                url = decodeNewsItem.url,
            )
        }
    }

    override fun onClickSaveIcon(detailsUiState: DetailsUiState) {
        tryToExecute(
            call = {
                manageSaveLaterUseCase.saveNewsForLater(detailsUiState.toNewsItemEntity())
            },
            onSuccess = { onSaveNewsSuccess(detailsUiState) },
            onError = { onError(it) }
        )
    }

    override fun onClickBackButton() {
        sendUiEffect(DetailsUiEffect.NavigateBack)
    }

    private fun onSaveNewsSuccess(detailsUiState: DetailsUiState) {
        _state.update { it.copy(changeSavedIconColor = !detailsUiState.changeSavedIconColor) }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }

}
