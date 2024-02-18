package com.finance.hodl.exchange.bithumb

import com.finance.hodl.exchange.ExchangeClient
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import java.math.BigDecimal
import javax.inject.Inject


internal class BithumbExchange @Inject constructor(
    private val publicRestApi: PublicApiService,
) : ExchangeClient {

    override fun name():String = "Real Bithumb Exchange"

    override suspend fun getPrice(ticker: String, currency: String):BigDecimal {
        return publicRestApi.getTicker(ticker, currency).data.closing_price.toBigDecimal()
    }

    override suspend fun allTickers(currency: String):List<String> {
        return publicRestApi.getAllTicker(currency).data.tickers.keys.toList()
    }
}