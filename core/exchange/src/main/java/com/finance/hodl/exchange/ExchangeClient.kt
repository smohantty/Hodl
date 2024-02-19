package com.finance.hodl.exchange

import com.finance.hodl.model.data.LimitOrder
import com.finance.hodl.model.data.OrderId
import java.math.BigDecimal

interface ExchangeClient {
    fun name():String

    suspend fun allTickers(currency: String):List<String>
    suspend fun getPrice(ticker: String, currency: String): BigDecimal

    suspend fun placeLimitOrder(order: LimitOrder): Result<OrderId>;
}