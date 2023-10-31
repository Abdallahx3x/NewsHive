package com.example.entities.news

import java.util.Date

data class NewsItemEntity(
    val news: NewsEntity,
    val publishedTime: Date
)
