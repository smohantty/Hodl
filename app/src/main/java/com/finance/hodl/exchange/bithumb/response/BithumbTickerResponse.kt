@file:UseSerializers(BigDecimalAsPlainStringSerializer::class, BigIntegerAsPlainStringSerializer::class)
package com.finance.hodl.exchange.bithumb.response

import com.finance.hodl.lib.BigDecimalAsPlainStringSerializer
import com.finance.hodl.lib.BigIntegerAsPlainStringSerializer
import kotlinx.serialization.UseSerializers



import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.math.BigInteger

@Serializable
data class BithumbSingleTickerResponse(
    val status: String,
    val data: BithumbSingleTickerData
)

@Serializable
data class BithumbSingleTickerData(
    val opening_price:BigDecimal,
    val closing_price:BigDecimal,
    val min_price:BigDecimal,
    val max_price:BigDecimal,
    val units_traded:BigDecimal,
    val acc_trade_value:BigDecimal,
    val prev_closing_price:BigDecimal,
    val units_traded_24H:BigDecimal,
    val acc_trade_value_24H: BigDecimal,
    val fluctate_24H: BigDecimal,
    val fluctate_rate_24H: BigDecimal,
    val date:BigInteger
)

data class RecentTransactionsResponse(
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
data class BithumbAllTickerResponse(
    val status: String,
    val data: Map<String, TickerAllTickerResponseData>,
    val date: String,
)

@Serializable
data class TickerAllTickerResponseData(
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
data class BithumbOrderBookResponse(
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

