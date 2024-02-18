package com.finance.hodl.exchange

import java.math.BigDecimal
import java.util.Currency

interface ExchangeClient {
    fun name():String

    suspend fun allTickers(currency: String):List<String>
    suspend fun getPrice(ticker: String, currency: String): BigDecimal
}