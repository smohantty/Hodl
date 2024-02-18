package com.finance.hodl.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.finance.hodl.database.model.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions")
    fun observeAllTransaction(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE botId = :botId")
    fun observeTransactionFor(botId: String): Flow<List<TransactionEntity>>

    @Upsert
    suspend fun upsert(bot: TransactionEntity)
}