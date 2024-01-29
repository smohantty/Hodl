package com.finance.hodl.exchange.bithumb

import com.finance.hodl.exchange.bithumb.response.BithumbAllTickerResponse
import com.finance.hodl.exchange.bithumb.response.BithumbOrderBookResponse
import com.finance.hodl.exchange.bithumb.response.BithumbSingleTickerResponse
import com.finance.hodl.exchange.bithumb.response.RecentTransactionsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BithumbPublicApiService {

    @GET("public/ticker/{crypto}_{currency}")
    suspend fun getTicker(
        @retrofit2.http.Path("crypto") crypto: String,
        @retrofit2.http.Path("currency") currency: String
    ): BithumbSingleTickerResponse

    @GET("public/transaction_history/{crypto}_{currency}")
    suspend fun getRecentTransactionsForTicker(
        @retrofit2.http.Path("crypto") crypto: String,
        @retrofit2.http.Path("currency") currency: String
    ): RecentTransactionsResponse


    @GET("public/orderbook/{crypto}_{currency}")
    suspend fun getOrderBook(
        @retrofit2.http.Path("crypto") crypto: String,
        @retrofit2.http.Path("currency") currency: String
    ): BithumbOrderBookResponse

    @GET("public/ticker/ALL_{currency}")
    suspend fun getAllTicker(
        @retrofit2.http.Path("currency") currency: String
    ): BithumbAllTickerResponse

    companion object {
        private const val BASE_URL = "https://api.bithumb.com/"

        fun create(): BithumbPublicApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(BithumbPublicApiService::class.java)
        }
    }
}
