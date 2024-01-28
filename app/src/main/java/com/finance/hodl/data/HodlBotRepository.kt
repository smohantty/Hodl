package com.finance.hodl.data

import com.finance.hodl.data.source.local.BotStatus
import com.finance.hodl.data.source.local.BotType
import com.finance.hodl.data.source.local.HodlBot
import com.finance.hodl.data.source.local.HodlBotDao
import com.finance.hodl.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.math.BigDecimal
import java.math.BigInteger
import java.util.Currency
import java.util.UUID
import javax.inject.Inject

class HodlBotRepository @Inject constructor(
    private val botDataSource: HodlBotDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend fun createBot(ticker: String, currency: String, low: BigDecimal , high: BigDecimal, gridCount:Int): String {

        val botId = withContext(dispatcher) {
            createBotId()
        }
        val bot = HodlBot(
            id = botId,
            ticker = ticker,
            currency = currency,
            low = low,
            high = high,
            girdCount = gridCount,
            type = BotType.SPOT_GRID,
            status = BotStatus.CREATED
        )
        botDataSource.upsert(bot)
        return botId
    }

    private fun createBotId() : String {
        return UUID.randomUUID().toString()
    }
}