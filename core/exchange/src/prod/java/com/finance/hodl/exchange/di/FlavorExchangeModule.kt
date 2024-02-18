package com.finance.hodl.exchange.di


import com.finance.hodl.exchange.ExchangeClient
import com.finance.hodl.exchange.network.BithumbExchange
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavorExchangeModule {

    @Binds
    fun bindBithumbExchange(impl: BithumbExchange): ExchangeClient
}