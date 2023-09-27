package com.example.viewmodel

import android.annotation.SuppressLint
import com.example.entities.news.NewsItemEntity
import com.example.viewmodel.home.NewsUiState
import com.example.viewmodel.home.NewsItemUiState
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("SimpleDateFormat")
val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

@JvmName("breakingNewsToBreakingNewsUiState")
fun NewsItemEntity.toBreakingNewsUiState(): NewsUiState {
    return NewsUiState(
        title = this.news.title,
        imageUrl = this.news.imageUrl,
        content = this.news.content,
        category = this.news.title,
        url = this.news.url
    )
}
@JvmName("breakingNewsToBreakingNewsUiState")
fun List<NewsItemEntity>.toBreakingNewsUiState(): List<NewsUiState> = this.map { it.toBreakingNewsUiState() }

@JvmName("classifiedNewsToRecommendedNewsUiState")
fun NewsItemEntity.toNewsItemUiState(): NewsItemUiState {
    val publishedTimeString = dateFormat.format(this.publishedTime)
    return NewsItemUiState(
        title = this.news.title,
        imageUrl = this.news.imageUrl,
        category = this.news.category,
        publishedAt =publishedTimeString,
    )
}
@JvmName("classifiedNewsToRecommendedNewsUiState")
fun List<NewsItemEntity>.toNewsItemUiState(): List<NewsItemUiState> =
    this.map { it.toNewsItemUiState() }




fun NewsUiState.toEncodeNewsUiState(): NewsUiState {
    val encodedImageUrl = URLEncoder.encode(this.imageUrl, StandardCharsets.UTF_8.toString())
    val encodedUrl = URLEncoder.encode(this.url, StandardCharsets.UTF_8.toString())
    return NewsUiState(
        title = this.title,
        imageUrl = encodedImageUrl,
        content = this.content,
        url = encodedUrl
    )
}


fun NewsUiState.toDecodeNewsUiState(): NewsUiState {
    val decodeImageUrl = URLDecoder.decode(this.imageUrl, StandardCharsets.UTF_8.toString())
    val decodeUrl = URLDecoder.decode(this.url, StandardCharsets.UTF_8.toString())
    return NewsUiState(
        title = this.title,
        imageUrl = decodeImageUrl,
        content = this.content,
        url = decodeUrl
    )
}


//
//@JvmName("classifiedNewsToDiscoverNewsUiState")
//fun NewsEntity.toDiscoverNewsUiState(): CategoryNewsUiState {
//    return CategoryNewsUiState(
//        title = this.title,
//        imageUrl = this.imageUrl,
//        categoryName = this.category,
//        publishedAt = this.publishedAt,
//    )
//}
//@JvmName("classifiedNewsToDiscoverNewsUiState")
//fun List<NewsEntity>.toDiscoverNewsUiState(): List<CategoryNewsUiState> =
//    this.map { it.toDiscoverNewsUiState() }