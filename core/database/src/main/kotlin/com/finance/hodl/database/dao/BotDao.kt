package com.finance.hodl.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.finance.hodl.database.model.BotEntity
import com.finance.hodl.model.data.BotStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface BotDao {

    @Query("SELECT * FROM bots")
    fun observeAll(): Flow<List<BotEntity>>

    @Query("SELECT * FROM bots where status = :botStatus")
    fun observeAllWithStatus(botStatus: BotStatus): Flow<List<BotEntity>>

    @Upsert
    suspend fun upsert(bot: BotEntity)

    @Upsert
    suspend fun upsertAll(bots: List<BotEntity>)

    @Query("UPDATE bots SET status = :botStatus WHERE id = :botId")
    suspend fun updateStatus(botId: String, botStatus: BotStatus)

    @Query("DELETE FROM bots")
    suspend fun deleteAll()
}