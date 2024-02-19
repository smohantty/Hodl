
package com.finance.hodl.exchange.bithumb.fake

import android.util.Log
import com.finance.hodl.exchange.ExchangeClient
import com.finance.hodl.exchange.bithumb.PrivateAccountApiService
import com.finance.hodl.exchange.bithumb.PrivateTradeApiService
import com.finance.hodl.exchange.bithumb.PublicApiService
import com.finance.hodl.exchange.bithumb.getApiHeaders
import com.finance.hodl.model.data.LimitOrder
import com.finance.hodl.model.data.OrderId
import java.math.BigDecimal
import javax.inject.Inject

/**
 * [ExchangeClient] implementation that provides fake bithumb service
 */
internal class FakeBithumbExchange @Inject constructor(
    private val publicRestApi: PublicApiService,
    private val tradeApi: PrivateTradeApiService,
    private val accountApi: PrivateAccountApiService,
) : ExchangeClient {
    override fun name():String = "Fake Bithumb Exchange"

    override suspend fun getPrice(ticker: String, currency: String):BigDecimal {
        return publicRestApi.getTicker(ticker, currency).data.closing_price.toBigDecimal()
    }

    override suspend fun allTickers(currency: String):List<String> {
        return publicRestApi.getAllTicker(currency).data.tickers.keys.toList()
    }

    override suspend fun placeLimitOrder(order: LimitOrder): Result<OrderId> {
        val params = mapOf("order_currency" to order.ticker,
            "payment_currency" to order.paymentCurrency,
            "units" to order.paymentAmount.toString(),
            "price" to order.limitPrice.toLong().toString(),
            "type" to order.type.name,
        )
        val headers = getApiHeaders("/trade/place", params, "your_api_key", "your_api_secret")
        return try {
            val response = tradeApi.placeTrade(
                tradeRequestParams = headers["params"]!!,
                apiKey = headers["Api-Key"]!!,
                apiSign = headers["Api-Sign"]!!,
                apiNonce = headers["Api-Nonce"]!!,
            )
            Log.d("SUV", "Response = $response")
            Result.success(OrderId(response.order_id))
        } catch (e:Exception) {
            Result.failure(e)
        }
    }

}