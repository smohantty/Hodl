package com.finance.hodl.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bot")
data class HodlBot(
    @PrimaryKey val id: String,  // Unique id to distinguish this bot
    val ticker: String,          // ex: BTC , ETH etc
    val currency: String,        // ex: KRW , BTC, USDT market
    val type: BotType,
    val status: BotStatus,
)

enum class BotStatus {
    RUNNING,
    STOPPED
}

enum class BotType {
    SPOT_GRID,
    KNIFE_CATCH
}