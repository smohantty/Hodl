package com.finance.hodl.data.source.network.bithumb

import com.finance.hodl.data.source.network.CryptoExchangeClient
import com.finance.hodl.data.source.network.bithumb.private.account.AccountApiService
import com.finance.hodl.data.source.network.bithumb.private.trade.TradeApiService
import com.finance.hodl.data.source.network.bithumb.public.PublicApiService

class BithumbClient(
    private val publicApiService: PublicApiService,
    private val accountApiService: AccountApiService,
    private val tradeApiService: TradeApiService,
) : CryptoExchangeClient {

}
