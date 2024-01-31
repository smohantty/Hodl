package com.crypto.exchange.bithumb.public

import com.crypto.exchange.bithumb.private.account.AccountApiService
import com.crypto.exchange.bithumb.private.trade.TradeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BithumbClient(
    private val publicApiService: PublicApiService,
    private val accountApiService: AccountApiService,
    private val tradeApiService: TradeApiService,
)  {


    companion object {
        private const val BASE_URL = "https://api.bithumb.com/"

        fun create(): PublicApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PublicApiService::class.java)
        }
    }
}
