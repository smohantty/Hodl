package com.finance.hodl.exchange.bithumb

import com.finance.hodl.exchange.ExchangeClient
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import src.main.com.finance.hodl.model.data.LimitOrder
import src.main.com.finance.hodl.model.data.OrderInfo
import src.main.com.finance.hodl.model.data.OrderType
import java.math.BigDecimal
import javax.inject.Inject


internal class BithumbExchange @Inject constructor(
    private val publicRestApi: PublicApiService,
    private val tradeApi: BithumbTradeApiService,
) : ExchangeClient {

    override fun name():String = "Real Bithumb Exchange"

    override suspend fun getPrice(ticker: String, currency: String):BigDecimal {
        return publicRestApi.getTicker(ticker, currency).data.closing_price.toBigDecimal()
    }

    override suspend fun allTickers(currency: String):List<String> {
        return publicRestApi.getAllTicker(currency).data.tickers.keys.toList()
    }

    override suspend fun placeLimitOrder(order: LimitOrder): OrderInfo {
        return OrderInfo(OrderType.BUY, "", "", BigDecimal(0), BigDecimal(0))
    }
}