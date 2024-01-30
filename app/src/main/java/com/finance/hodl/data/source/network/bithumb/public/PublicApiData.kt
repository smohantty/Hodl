@file:UseSerializers(BigDecimalAsPlainStringSerializer::class, BigIntegerAsPlainStringSerializer::class)
package com.finance.hodl.data.source.network.bithumb.public

import com.finance.hodl.exchange.bithumb.response.Order
import com.finance.hodl.exchange.bithumb.response.OrderBookData
import com.finance.hodl.lib.BigDecimalAsPlainStringSerializer
import com.finance.hodl.lib.BigIntegerAsPlainStringSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.math.BigInteger

@Serializable
data class SingleTicker(
    val status: String,
    val data: SingleTickerData
)

@Serializable
data class SingleTickerData(
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

data class RecentTransactions(
    val status: String,
    val data: List<Transaction>
)

data class Transaction(
    val transaction_date: String,
    val type: String,
    val units_traded: Double,
    val price: Long,
    val total: Long
)

@Serializable
data class AllTicker(
    val status: String,
    val data: Map<String, AllTickerData>,
    val date: String,
)

@Serializable
data class AllTickerData(
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

@Serializable
data class OrderBook(
    val status: String,
    val data: OrderBookData
)

@Serializable
data class OrderBookData(
    val timestamp: BigInteger,
    val payment_currency: String,
    val order_currency: String,
    val bids: List<Order>,
    val asks: List<Order>
)

@Serializable
data class Order(
    val price: BigDecimal,
    val quantity: BigDecimal
)
