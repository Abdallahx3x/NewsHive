package com.example.repositories.mapper

import com.example.entities.news.BreakingNews
import com.example.repositories.dto.DataDto

fun DataDto.toBreakingNews(): BreakingNews {
    return BreakingNews(
        author = this.author ?: "",
        title = this.title ?: "",
        imageUrl = this.url ?: "",
    )
}

