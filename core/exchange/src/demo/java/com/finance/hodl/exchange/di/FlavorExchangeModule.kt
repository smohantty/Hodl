package com.finance.hodl.exchange.di


import com.finance.hodl.exchange.ExchangeClient
import com.finance.hodl.exchange.bithumb.fake.FakeBithumbExchange
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FlavorExchangeModule {

    @Binds
    fun bindFakeBithumbExchange(impl: FakeBithumbExchange): ExchangeClient
}