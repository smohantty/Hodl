package com.finance.hodl.exchange.bithumb

import com.finance.hodl.exchange.ExchangeClient
import com.finance.hodl.model.data.LimitOrder
import com.finance.hodl.model.data.OrderId
import java.math.BigDecimal
import javax.inject.Inject


internal class BithumbExchange @Inject constructor(
    private val publicRestApi: PublicApiService,
    private val tradeApi: PrivateTradeApiService,
    private val accountApi: PrivateAccountApiService,
) : ExchangeClient {

    override fun name():String = "Real Bithumb Exchange"

    override suspend fun getPrice(ticker: String, currency: String):BigDecimal {
        return publicRestApi.getTicker(ticker, currency).data.closing_price.toBigDecimal()
    }

    override suspend fun allTickers(currency: String):List<String> {
        return publicRestApi.getAllTicker(currency).data.tickers.keys.toList()
    }

    override suspend fun placeLimitOrder(order: LimitOrder): Result<OrderId> {
        return Result.failure(Exception("Not implemented"))
    }
}