package com.finance.hodl.data.di

import com.finance.hodl.data.repository.BotRepository
import com.finance.hodl.data.repository.ExchangeRepository
import com.finance.hodl.database.dao.BotDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataModule {
}