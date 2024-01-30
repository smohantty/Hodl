package com.finance.hodl.data.source.network.bithumb.public

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PublicApiService {

    @GET("public/ticker/{crypto}_{currency}")
    suspend fun getTicker(
        @retrofit2.http.Path("crypto") crypto: String,
        @retrofit2.http.Path("currency") currency: String
    ): SingleTicker

    @GET("public/transaction_history/{crypto}_{currency}")
    suspend fun getRecentTransactions(
        @retrofit2.http.Path("crypto") crypto: String,
        @retrofit2.http.Path("currency") currency: String
    ): RecentTransactions


    @GET("public/orderbook/{crypto}_{currency}")
    suspend fun getOrderBook(
        @retrofit2.http.Path("crypto") crypto: String,
        @retrofit2.http.Path("currency") currency: String
    ): OrderBook

    @GET("public/ticker/ALL_{currency}")
    suspend fun getAllTicker(
        @retrofit2.http.Path("currency") currency: String
    ): AllTicker

    companion object {
        private const val BASE_URL = "https://api.bithumb.com/"

        fun create(): PublicApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PublicApiService::class.java)
        }
    }
}
