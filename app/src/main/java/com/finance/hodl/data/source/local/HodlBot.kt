package com.finance.hodl.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey


enum class BotStatus {
    RUNNING,
    STOPPED
}

@Entity(tableName = "bot")
data class HodlBot(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val status: BotStatus,
)