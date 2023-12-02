package com.example.viewmodel.details

import com.example.viewmodel.base.BaseViewModel

sealed interface DetailsUiEffect:BaseViewModel.BaseUiEffect  {
    object NavigateBack : DetailsUiEffect
}
