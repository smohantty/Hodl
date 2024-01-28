package com.finance.hodl.data.source.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "bot_transaction",
    foreignKeys = [
        ForeignKey(
            entity = HodlBot::class,
            parentColumns = ["id"],
            childColumns = ["botId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HodlBotTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val botId: String,  // transaction made by this bot
)