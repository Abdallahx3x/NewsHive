package com.example.viewmodel.details

import com.example.viewmodel.base.BaseUiState

data class DetailsUiState(
    override val title: String = "",
    override val imageUrl: String = "",
    override val content: String = "",
    override val url: String = "",
    val changeSavedIconColor: Boolean = false,
    val error: String? = null
) : BaseUiState()
