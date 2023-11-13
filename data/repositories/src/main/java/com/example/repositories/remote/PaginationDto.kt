package com.example.repositories.remote


import com.google.gson.annotations.SerializedName

data class PaginationDto(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("offset")
    val offset: Int? = null,
    @SerializedName("total")
    val total: Int? = null
)