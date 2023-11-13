package com.example.newshive.di

import com.example.local.RoomDataSource
import com.example.remote.NewsHiveRetrofitDataSource
import com.example.repositories.local.LocalDataStore
import com.example.repositories.NewsHiveRepositoryImpl
import com.example.repositories.remote.RemoteDataStore
import com.example.usecases.NewsHiveRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindRepository(
        getAllNewsRepositoryImplementation: NewsHiveRepositoryImpl
    ): NewsHiveRepository


    @Binds
    @Singleton
    abstract fun bindNewsHiveRemoteData(
        newsHiveRetrofitDataSource: NewsHiveRetrofitDataSource
    ): RemoteDataStore

    @Binds
    @Singleton
    abstract fun bindNewsHiveLocalData(
        roomDataSource: RoomDataSource
    ): LocalDataStore



}