package com.khun.smartstream.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.khun.smartstream.data.remote.MovieApi
import com.khun.smartstream.data.util.interceptors.ApiKeyInterceptor
import com.khun.smartstream.data.util.interceptors.HeaderInterceptor
import com.khun.smartstream.data.util.interceptors.NetworkConnectionInterceptor
import com.khun.smartstream.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkServiceModule {

    @Provides
    @BASEURL
    fun provideBaseUrl(): String = BASE_URL

    @Provides
    @Singleton
    fun providesOkHttpInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor =
        HeaderInterceptor()

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(): ApiKeyInterceptor =
        ApiKeyInterceptor()

    @Provides
    @Singleton
    fun provideNetworkConnectionInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor =
        NetworkConnectionInterceptor(context)

    @Provides
    @Singleton
    fun providesHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HeaderInterceptor,
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .addInterceptor(networkConnectionInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(@BASEURL baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true // Allows deserialization to ignore unknown fields
        }
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

}