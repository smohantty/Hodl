
package com.finance.hodl.exchange.bithumb.fake

import android.util.Log
import com.finance.hodl.exchange.ExchangeClient
import com.finance.hodl.exchange.bithumb.BithumbTradeApiService
import com.finance.hodl.exchange.bithumb.PublicApiService
import com.finance.hodl.exchange.bithumb.TradeRequest
import com.finance.hodl.exchange.bithumb.averagePrice
import com.finance.hodl.exchange.bithumb.getApiHeaders
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import src.main.com.finance.hodl.model.data.LimitOrder
import src.main.com.finance.hodl.model.data.OrderInfo
import java.math.BigDecimal
import javax.inject.Inject

/**
 * [ExchangeClient] implementation that provides fake bithumb service
 */
internal class FakeBithumbExchange @Inject constructor(
    private val publicRestApi: PublicApiService,
    private val tradeApi: BithumbTradeApiService,
) : ExchangeClient {
    override fun name():String = "Fake Bithumb Exchange"

    override suspend fun getPrice(ticker: String, currency: String):BigDecimal {
        return publicRestApi.getTicker(ticker, currency).data.closing_price.toBigDecimal()
    }

    override suspend fun allTickers(currency: String):List<String> {
        return publicRestApi.getAllTicker(currency).data.tickers.keys.toList()
    }

    override suspend fun placeLimitOrder(order: LimitOrder): OrderInfo {
        return placeLimitOrderHelper(order)
    }

    private suspend fun placeLimitOrderHelper(order: LimitOrder): OrderInfo {
        val request = TradeRequest(
            order_currency = order.ticker,
            payment_currency = order.paymentCurrency,
            units = order.paymentAmount.toFloat(),
            price = order.limitPrice.toInt(),
            type = order.type.toString()
        )

        val params = mapOf("order_currency" to request.order_currency,
            "payment_currency" to request.payment_currency,
            "units" to request.units.toString(),
            "price" to request.price.toString(),
            "type" to request.type,
        )
        val headers = getApiHeaders("/trade/place", params, "your_api_key", "your_api_secret")

        val response = tradeApi.placeTrade(
            tradeRequestParams = headers["params"]!!,
            apiKey = headers["Api-Key"]!!,
            apiSign = headers["Api-Sign"]!!,
            apiNonce = headers["Api-Nonce"]!!,
        )
        Log.d("SUV", "Response = $response")

        return OrderInfo(order.type, order.ticker, order.paymentCurrency, order.paymentAmount, order.limitPrice)
    }
}