package com.example.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.repositories.local.NewsLocalDto


@Database(entities = [NewsLocalDto::class],version = 1)
abstract class NewsHiveDatabase: RoomDatabase() {
    abstract fun newsHiveDao():NewsHiveDao
}