package com.example.entities.news

import java.util.Date

data class DetailsNew(
    val author: String,
    val title: String,
    val content: String,
    val url: String,
    val category: String,
    val imageUrl: String,
    val publishedTime: Date
)
