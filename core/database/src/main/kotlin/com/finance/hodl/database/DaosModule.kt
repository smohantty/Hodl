package com.finance.hodl.database

import com.finance.hodl.database.dao.BotDao
import com.finance.hodl.database.dao.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
    @Provides
    fun providesBotsDao(
        database: HodlDatabase,
    ): BotDao = database.botDao()

    @Provides
    fun providesTransactionsDao(
        database: HodlDatabase,
    ): TransactionDao = database.transactionDao()

}