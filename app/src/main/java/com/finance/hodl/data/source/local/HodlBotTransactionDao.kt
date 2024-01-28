package com.finance.hodl.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface HodlBotTransactionDao {

    @Query("SELECT * FROM bot_transaction")
    fun observeAllTransaction(): Flow<List<HodlBotTransaction>>

    @Query("SELECT * FROM bot_transaction WHERE botId = :botId")
    fun observeTransactionFor(botId: String): Flow<List<HodlBotTransaction>>

    @Upsert
    suspend fun upsert(bot: HodlBotTransaction)
}