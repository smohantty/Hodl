package com.finance.hodl.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [HodlBot::class], version = 1, exportSchema = false)
abstract class HodlDatabase : RoomDatabase() {

    abstract fun hodlBotDao(): HodlBotDao
}
