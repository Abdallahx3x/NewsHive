package com.example.repositories.dto


import com.google.gson.annotations.SerializedName

data class NewsResponseDto(
    @SerializedName("data")
    val data: List<DataDto?>? = null,
    @SerializedName("pagination")
    val pagination: PaginationDto? = null
)