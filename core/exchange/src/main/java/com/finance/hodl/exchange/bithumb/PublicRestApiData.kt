package com.finance.hodl.exchange.bithumb

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.math.BigDecimal
import java.math.BigInteger


@Serializable
internal data class NetworkResponse<T>(
    val status: String,
    val data: T,
)

@Serializable
data class AccountInfo(
    val created: Long,
    val account_id: String,
    val order_currency: String,
    val payment_currency: String,
    val trade_fee: String,
    val balance: String
)


@Serializable
internal data class BalanceInfo(
    val status: String, //TODO
)

@Serializable
internal data class SingleTicker(
    val opening_price: String,
    val closing_price: String,
    val min_price: String,
    val max_price: String,
    val units_traded: String,
    val acc_trade_value: String,
    val prev_closing_price: String,
    val units_traded_24H: String,
    val acc_trade_value_24H: String,
    val fluctate_24H: String,
    val fluctate_rate_24H: String,
    val date: String,
)

internal fun List<Transaction>.averagePrice(): BigDecimal {
    return if (isEmpty()) {
        throw Exception("Empty Transaction")
    } else {
        sumOf { it.price }.toBigDecimal() / size.toBigDecimal()
    }
}

@Serializable
internal data class Transaction(
    val transaction_date: String,
    val type: String,
    val units_traded: Double,
    val price: Long,
    val total: Long
)

@Serializable
internal data class AllTicker(
    val tickers: Map<String, TickerData>,
    val date: String,
)

@Serializable
internal data class TickerData(
    val opening_price: String,
    val closing_price: String,
    val min_price: String,
    val max_price: String,
    val units_traded: String,
    val acc_trade_value: String,
    val prev_closing_price: String,
    val units_traded_24H: String,
    val acc_trade_value_24H: String,
    val fluctate_24H: String,
    val fluctate_rate_24H: String
)

internal data class OrderBook(
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
