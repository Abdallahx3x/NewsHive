package com.example.newshive.di

import com.example.remote.NewsHiveRetrofitDataSource
import com.example.repositories.NewsHiveRepositoryImpl
import com.example.repositories.RemoteDataStore
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
    abstract fun bindNewsHiveData(
        newsHiveRetrofitDataSource: NewsHiveRetrofitDataSource
    ): RemoteDataStore


}