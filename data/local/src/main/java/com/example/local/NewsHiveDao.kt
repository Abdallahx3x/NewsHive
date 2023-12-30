package com.example.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.repositories.local.NewsLocalDto
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsHiveDao {
    @Query("SELECT * FROM news_table")
    fun getAllSavedNews(): Flow<List<NewsLocalDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateNews(newsLocalDto: NewsLocalDto)

    @Query("DELETE FROM news_table WHERE title =:title ")
    suspend fun deleteSavedNewsByTitle(title:String)

}