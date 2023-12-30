package com.example.viewmodel.home

import com.example.viewmodel.base.BaseViewModel

sealed interface HomeUiEffect:BaseViewModel.BaseUiEffect{
    data class NavigateToDetails(
        val newsItem:String
    ):HomeUiEffect
   object NavigateToViewALl:HomeUiEffect
}