package com.example.repositories.mapper

import com.example.entities.news.LatestNews
import com.example.repositories.dto.DataDto

fun DataDto.toLatestNews(): LatestNews {
    return LatestNews(
        category = this.category ?: "",
        title = this.title ?: "",
        imageUrl = this.image ?: "",
        publishedAt = this.publishedAt?:""
    )
}
