package com.finance.hodl.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.math.BigDecimal

class BigDecimalConverter {
    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? {
        return value?.toBigDecimal()
    }
}




@Database(entities = [HodlBot::class, HodlBotTransaction::class], version = 1, exportSchema = false)
@TypeConverters(BigDecimalConverter::class)
abstract class HodlDatabase : RoomDatabase() {

    abstract fun hodlBotDao(): HodlBotDao
    abstract fun hodlBotTransactionDao(): HodlBotTransactionDao
}
