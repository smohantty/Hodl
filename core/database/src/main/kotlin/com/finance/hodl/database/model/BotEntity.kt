package com.finance.hodl.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import src.main.com.finance.hodl.model.data.BotStatus
import src.main.com.finance.hodl.model.data.BotType
import src.main.com.finance.hodl.model.data.HodlBot
import java.math.BigDecimal
import java.math.BigInteger


@Entity(tableName = "bots")
data class BotEntity(
    @PrimaryKey
    val id: String,              // Unique id to distinguish this bot
    val ticker: String,          // ex: BTC , ETH etc
    val currency: String,        // ex: KRW , BTC, USDT market
    val low: BigDecimal,
    val high: BigDecimal,
    val girdCount: Int,
    val type: BotType,
    val status: BotStatus,
)

fun BotEntity.asExternalModel() = HodlBot(
    id = id,
    ticker = ticker,
    currency = currency,
    low = low,
    high = high,
    girdCount = girdCount,
    type = type,
    status = status,
)
