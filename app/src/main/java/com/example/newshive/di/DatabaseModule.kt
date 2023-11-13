package com.example.newshive.di

import android.content.Context
import androidx.room.Room
import com.example.local.NewsHiveDao
import com.example.local.NewsHiveDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DATABASE_NAME = "databaseName"
    @Provides
    @Singleton
    fun provideNewsDoa(newsHiveDatabase: NewsHiveDatabase): NewsHiveDao {
        return newsHiveDatabase.newsHiveDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Named(DATABASE_NAME) databaseName: String
    ): NewsHiveDatabase {
        return Room.databaseBuilder(context, NewsHiveDatabase::class.java, databaseName).build()
    }

    @Provides
    @Singleton
    @Named(DATABASE_NAME)
    fun provideNewsDatabaseName(): String {
        return "news_table"
    }
}