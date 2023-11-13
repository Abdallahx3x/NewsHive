package com.example.repositories.mapper

import android.annotation.SuppressLint
import com.example.entities.news.NewsEntity
import com.example.entities.news.NewsItemEntity
import com.example.repositories.local.NewsLocalDto
import com.example.repositories.remote.DataDto
import java.text.SimpleDateFormat
import java.util.Date


fun DataDto?.toNewsEntity(): NewsEntity {
    return NewsEntity(
        category = this?.category ?: "",
        title = this?.title ?: "",
        imageUrl = this?.image ?: "",
        content = this?.description ?: "",
        url = this?.url ?: ""
    )
}

@SuppressLint("SimpleDateFormat")
fun DataDto?.toNewsItemEntity(): NewsItemEntity {
    val publishedTime = try {
        SimpleDateFormat("yyyy-MM-dd").parse(this?.publishedAt ?: "")
    } catch (e: Exception) {
        Date()
    }
    return NewsItemEntity(
        news = this.toNewsEntity(),
        publishedTime = publishedTime
    )
}


fun NewsItemEntity.toNewsLocalDto(): NewsLocalDto {
    return NewsLocalDto(
        title = news.title,
        content = news.content,
        url = news.url,
        imageUrl = news.imageUrl,
        publishedAt = "",
        category = news.category
    )
}


fun NewsLocalDto.toNewsItemEntity(): NewsItemEntity {
    return NewsItemEntity(
        news = NewsEntity(
            title = title,
            content = content,
            url = url,
            imageUrl = imageUrl,
            category = category
        ),
        publishedTime = Date()
    )
}
fun List<NewsLocalDto>.toNewsItemEntities():List<NewsItemEntity> = this.map { it.toNewsItemEntity() }