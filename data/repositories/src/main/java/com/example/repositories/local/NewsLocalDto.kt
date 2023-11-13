package com.example.repositories.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "news_table", indices = [Index(value = ["title"], unique = true)])
data class NewsLocalDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val imageUrl: String,
    val category: String,
    val url: String,
    val publishedAt: String
)
