package com.finance.hodl.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "bot")
data class HodlBot(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
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