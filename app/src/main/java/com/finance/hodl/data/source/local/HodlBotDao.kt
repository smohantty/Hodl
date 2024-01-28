package com.finance.hodl.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface HodlBotDao {

    @Query("SELECT * FROM bot")
    fun observeAll(): Flow<List<HodlBot>>

    @Upsert
    suspend fun upsert(bot: HodlBot)

    @Upsert
    suspend fun upsertAll(bots: List<HodlBot>)

    @Query("UPDATE bot SET status = :botStatus WHERE id = :botId")
    suspend fun updateStatus(botId: Int, botStatus: BotStatus)

    @Query("DELETE FROM bot")
    suspend fun deleteAll()
}