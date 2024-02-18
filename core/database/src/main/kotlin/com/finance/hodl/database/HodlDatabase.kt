package com.finance.hodl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.finance.hodl.database.dao.BotDao
import com.finance.hodl.database.dao.TransactionDao
import com.finance.hodl.database.model.BotEntity
import com.finance.hodl.database.model.TransactionEntity
import com.finance.hodl.database.util.BigDecimalConverter

@Database(
    entities = [
        BotEntity::class,
        TransactionEntity::class,
    ],
    version = 1,
)
@TypeConverters(
    BigDecimalConverter::class,
)
internal abstract class HodlDatabase : RoomDatabase() {
    abstract fun botDao(): BotDao
    abstract fun transactionDao(): TransactionDao
}