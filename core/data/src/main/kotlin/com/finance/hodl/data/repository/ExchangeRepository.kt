package com.finance.hodl.data.repository

import com.finance.hodl.exchange.ExchangeClient
import src.main.com.finance.hodl.model.data.LimitOrder
import src.main.com.finance.hodl.model.data.OrderInfo
import java.math.BigDecimal
import javax.inject.Inject

class ExchangeRepository @Inject constructor(
    private val exchange: ExchangeClient
){
    fun name():String {
        return exchange.name();
    }

    suspend fun allTickers(currency: String):List<String> {
        return exchange.allTickers(currency)
    }

    suspend fun getPrice(ticker: String, currency: String): BigDecimal {
        return exchange.getPrice(ticker, currency)
    }

    suspend fun placeLimitOrder(order: LimitOrder): OrderInfo {
        return exchange.placeLimitOrder(order);
    }
}