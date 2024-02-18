package com.finance.hodl.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    @Singleton
    fun providesHodlDatabase(
        @ApplicationContext context: Context,
    ): HodlDatabase = Room.databaseBuilder(
        context,
        HodlDatabase::class.java,
        "hodl-database",
    ).build()
}
