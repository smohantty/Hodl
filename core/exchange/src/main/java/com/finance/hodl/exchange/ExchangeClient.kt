package com.finance.hodl.exchange

import src.main.com.finance.hodl.model.data.LimitOrder
import src.main.com.finance.hodl.model.data.OrderInfo
import java.math.BigDecimal
import java.util.Currency

interface ExchangeClient {
    fun name():String

    suspend fun allTickers(currency: String):List<String>
    suspend fun getPrice(ticker: String, currency: String): BigDecimal

    suspend fun placeLimitOrder(order: LimitOrder): OrderInfo;
}