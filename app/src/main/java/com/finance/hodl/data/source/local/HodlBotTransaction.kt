package com.finance.hodl.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bot_transaction")
data class HodlBotTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val botId: String,  // transaction made by this bot
)