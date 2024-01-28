package com.finance.hodl.di

import android.content.Context
import androidx.room.Room
import com.finance.hodl.data.source.local.HodlBotDao
import com.finance.hodl.data.source.local.HodlDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideHodlDataBase(@ApplicationContext context: Context): HodlDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HodlDatabase::class.java,
            "Hodl.db"
        ).build()
    }

    @Provides
    fun provideHodlBotDao(database: HodlDatabase) : HodlBotDao = database.hodlBotDao()
}
