package com.crypto.exchange.api

import java.math.BigDecimal
import java.math.BigInteger

data class Trade(
    val date: String,
    val type: String,
    val unitsTraded: Double,
    val price: Long,
    val total: Long
)

data class OrderBook(
    val timestamp: BigInteger,
    val ticker: String,
    val currency: String,
    val bids: List<Order>,
    val asks: List<Order>
)

data class Order(
    val price: BigDecimal,
    val quantity: BigDecimal
)

interface PublicApi {
    suspend fun getCurrentPrice(ticker: String, currency: String): BigDecimal

    suspend fun getRecentTrades(ticker: String, currency: String): List<Trade>

    suspend fun getOrderBook(ticker: String, currency: String) : OrderBook
}