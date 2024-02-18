
package com.finance.hodl.exchange.bithumb.fake

import com.finance.hodl.exchange.ExchangeClient
import com.finance.hodl.exchange.bithumb.PublicApiService
import com.finance.hodl.exchange.bithumb.averagePrice
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import java.math.BigDecimal
import javax.inject.Inject

/**
 * [ExchangeClient] implementation that provides fake bithumb service
 */
internal class FakeBithumbExchange @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: Call.Factory,
) : ExchangeClient {

    private val publicRestApi = Retrofit.Builder()
        .baseUrl(BITHUMB_BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(PublicApiService::class.java)

    override fun name():String = "Fake Bithumb Exchange"

    override suspend fun getPrice(ticker: String, currency: String):BigDecimal {
        return publicRestApi.getTicker(ticker, currency).data.closing_price.toBigDecimal()
    }

    override suspend fun allTickers(currency: String):List<String> {
        return publicRestApi.getAllTicker(currency).data.tickers.keys.toList()
    }

    companion object {
        private const val BITHUMB_BASE_URL = "https://api.bithumb.com/"
    }
}