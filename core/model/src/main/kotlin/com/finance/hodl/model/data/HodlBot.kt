package com.finance.hodl.model.data

import java.math.BigDecimal

enum class BotStatus {
    CREATED,
    ACTIVE,
    STOPPED
}

enum class BotType {
    SPOT_GRID,
    KNIFE_CATCH
}

data class HodlBot (
    val id: String,
    val ticker: String,
    val currency: String,
    val low: BigDecimal,
    val high: BigDecimal,
    val girdCount: Int,
    val type: BotType,
    val status: BotStatus,
)