package com.crypto.exchange.bithumb.public

import java.math.BigDecimal
import java.math.BigInteger

internal data class SingleTicker(
    val status: String,
    val data: SingleTickerData
)

internal data class SingleTickerData(
    val opening_price: BigDecimal,
    val closing_price: BigDecimal,
    val min_price: BigDecimal,
    val max_price: BigDecimal,
    val units_traded: BigDecimal,
    val acc_trade_value: BigDecimal,
    val prev_closing_price: BigDecimal,
    val units_traded_24H: BigDecimal,
    val acc_trade_value_24H: BigDecimal,
    val fluctate_24H: BigDecimal,
    val fluctate_rate_24H: BigDecimal,
    val date: BigInteger
)

internal data class RecentTransactions(
    val status: String,
    val data: List<Transaction>
)

internal data class Transaction(
    val transaction_date: String,
    val type: String,
    val units_traded: Double,
    val price: Long,
    val total: Long
)

internal data class AllTicker(
    val status: String,
    val data: Map<String, AllTickerData>,
    val date: String,
)


internal data class AllTickerData(
    val opening_price: String,
    val closing_price: String,
    val min_price: String,
    val max_price: String,
    val units_traded: String,
    val acc_trade_value: String,
    val prev_closing_price: String,
    val units_traded_24H: String,
    val acc_trade_value_24H: String,
    val fluctuate_24H: String,
    val fluctuate_rate_24H: String
)


internal data class OrderBook(
    val status: String,
    val data: OrderBookData
)


internal data class OrderBookData(
    val timestamp: BigInteger,
    val payment_currency: String,
    val order_currency: String,
    val bids: List<Order>,
    val asks: List<Order>
)


internal data class Order(
    val price: BigDecimal,
    val quantity: BigDecimal
)
