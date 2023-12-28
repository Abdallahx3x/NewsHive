package com.example.viewmodel.viewAll

interface ViewAllInteraction {
    fun onClickViewAllItem(viewAllItemUiState: ViewAllItemUiState)
    fun onRefreshData()
    fun onClickBackButton()
}