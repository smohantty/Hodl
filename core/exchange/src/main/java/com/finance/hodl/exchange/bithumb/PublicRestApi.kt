package com.finance.hodl.exchange.bithumb

import com.finance.hodl.exchange.bithumb.AllTicker
import com.finance.hodl.exchange.bithumb.OrderBook
import com.finance.hodl.exchange.bithumb.RecentTransactions
import com.finance.hodl.exchange.bithumb.SingleTicker
import retrofit2.http.GET

internal interface PublicApiService {

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
}
