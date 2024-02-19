package com.finance.hodl.exchange.di

import com.finance.hodl.exchange.bithumb.PrivateAccountApiService
import com.finance.hodl.exchange.bithumb.PrivateTradeApiService
import com.finance.hodl.exchange.bithumb.PublicApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    fun providesBithumbPrivateTradeApiService(
        networkJson: Json,
        okhttpCallFactory: Call.Factory
    ): PrivateTradeApiService = Retrofit.Builder()
        .baseUrl("https://api.bithumb.com/")
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(PrivateTradeApiService::class.java)

    @Provides
    fun providesBithumbPrivateAccountApiService(
        networkJson: Json,
        okhttpCallFactory: Call.Factory
    ): PrivateAccountApiService = Retrofit.Builder()
        .baseUrl("https://api.bithumb.com/")
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(PrivateAccountApiService::class.java)


    @Provides
    fun providesBithumbPublicRestApi(
        networkJson: Json,
        okhttpCallFactory: Call.Factory
    ): PublicApiService = Retrofit.Builder()
        .baseUrl("https://api.bithumb.com/")
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(PublicApiService::class.java)

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                },
        )
        .build()
}