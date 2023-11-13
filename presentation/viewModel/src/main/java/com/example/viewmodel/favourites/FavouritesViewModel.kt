package com.example.viewmodel.favourites

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.example.usecases.ManageSavedNewsUseCase
import com.example.viewmodel.base.BaseViewModel
import com.example.viewmodel.encode
import com.example.viewmodel.toFavouritesNewsUiState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val manageSaveLaterUseCase: ManageSavedNewsUseCase
) : BaseViewModel<FavouritesUiState, FavouritesUiEffect>(FavouritesUiState()),
    FavouritesInteraction {
    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            collectFlow(manageSaveLaterUseCase.getSavedNews()) {
                this.copy(favouritesItemUiState = it.toFavouritesNewsUiState())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClickFavouriteItem(favouritesItemUiState: FavouritesItemUiState) {
        val gson = Gson()
        val json = gson.toJson(favouritesItemUiState.encode())
        sendUiEffect(FavouritesUiEffect.NavigateToDetails(json))
    }

    override fun onDismissNews(title: String) {
        tryToExecute(
            call = { manageSaveLaterUseCase.deleteSavedNews(title) },
            onSuccess = { onDismissNewsSuccess() },
            onError = { onError(it) }
        )
    }

    private fun onDismissNewsSuccess() {}

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(error = throwable.message) }
    }
}




