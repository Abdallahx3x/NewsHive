package com.example.newshive.di

import com.example.remote.BuildConfig
import com.example.remote.NewsHiveInterceptor
import com.example.remote.NewsHiveService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpsLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        newsHiveInterceptor: NewsHiveInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(newsHiveInterceptor).build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(gsonConverterFactory).build()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NewsHiveService
    = retrofit.create(NewsHiveService::class.java)

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory
    = GsonConverterFactory.create()


}