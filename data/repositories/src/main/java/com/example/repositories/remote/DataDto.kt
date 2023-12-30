package com.example.repositories.remote


import com.google.gson.annotations.SerializedName

data class DataDto(
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("published_at")
    val publishedAt: String? = null,
    @SerializedName("source")
    val source: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null
)