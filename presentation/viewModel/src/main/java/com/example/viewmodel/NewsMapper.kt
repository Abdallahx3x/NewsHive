package com.example.viewmodel

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.entities.news.NewsItemEntity
import com.example.viewmodel.base.BaseUiState
import com.example.viewmodel.discover.CategoryNewsUiState
import com.example.viewmodel.home.BreakingNewsUiState
import com.example.viewmodel.home.RecommendedNewsUiState
import java.text.SimpleDateFormat
import java.util.Base64
import java.util.Locale

@SuppressLint("SimpleDateFormat")
val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

fun NewsItemEntity.toBreakingNewsUiState(): BreakingNewsUiState {
    return BreakingNewsUiState(
        title = this.news.title,
        imageUrl = this.news.imageUrl,
        content = this.news.content,
        url = this.news.url,
    )
}

fun List<NewsItemEntity>.toBreakingNewsUiState(): List<BreakingNewsUiState> =
    this.map { it.toBreakingNewsUiState() }


fun NewsItemEntity.toRecommendedNewsUiState(): RecommendedNewsUiState {
    val publishedTimeString = dateFormat.format(this.publishedTime)
    return RecommendedNewsUiState(
        title = this.news.title,
        imageUrl = this.news.imageUrl,
        content = this.news.content,
        category = this.news.category,
        url = this.news.url,
        publishedAt = publishedTimeString
    )
}

fun List<NewsItemEntity>.toRecommendedNewsUiState(): List<RecommendedNewsUiState> =
    this.map { it.toRecommendedNewsUiState() }


fun NewsItemEntity.toCategoryNewsUiState(): CategoryNewsUiState {
    val publishedTimeString = dateFormat.format(this.publishedTime)
    return CategoryNewsUiState(
        title = this.news.title,
        imageUrl = this.news.imageUrl,
        categoryName = this.news.category,
        url = this.news.url,
        content = this.news.content,
        publishedAt = publishedTimeString

    )
}

fun List<NewsItemEntity>.toCategoryNewsUiState(): List<CategoryNewsUiState> =
    this.map { it.toCategoryNewsUiState() }


@RequiresApi(Build.VERSION_CODES.O)
fun BaseUiState.encode(): BaseUiState {
    return BaseUiState(
        title = title.encodeBase64(),
        content = content.encodeBase64(),
        imageUrl = imageUrl.encodeBase64(),
        url = url.encodeBase64()
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun BaseUiState.decode(): BaseUiState {
    return BaseUiState(
        title = title.decodeBase64(),
        content = content.decodeBase64(),
        imageUrl = imageUrl.decodeBase64(),
        url = url.decodeBase64()
    )
}


@RequiresApi(Build.VERSION_CODES.O)
private fun String.encodeBase64(): String {
    return Base64.getUrlEncoder().encodeToString(this.toByteArray())
}

@RequiresApi(Build.VERSION_CODES.O)
private fun String.decodeBase64(): String {
    return String(Base64.getUrlDecoder().decode(this))
}
