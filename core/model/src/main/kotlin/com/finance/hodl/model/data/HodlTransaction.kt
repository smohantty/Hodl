package com.finance.hodl.model.data

import java.math.BigDecimal

data class HodlBotTransaction (
    val id: String,
)

sealed class OrderType(val name:String) {
    data object BUY : OrderType("buy")
    data object SELL : OrderType("sell")
}

data class LimitOrder(
    val type: OrderType,
    val ticker: String,
    val paymentCurrency: String,
    val paymentAmount: BigDecimal,
    val limitPrice: BigDecimal,
) {

}
data class OrderId(val id:String)

