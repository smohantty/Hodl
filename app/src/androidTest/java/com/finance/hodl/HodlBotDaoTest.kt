package com.finance.hodl

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.finance.hodl.data.source.local.BotStatus
import com.finance.hodl.data.source.local.BotType
import com.finance.hodl.data.source.local.HodlBot
import com.finance.hodl.data.source.local.HodlDatabase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HodlBotDaoTest {

    private lateinit var database: HodlDatabase

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            HodlDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun insertBotAndGetBot() = runTest {

        val bot = HodlBot(
            type = BotType.KNIFE_CATCH,
            status = BotStatus.RUNNING
        )

        database.hodlBotDao().upsert(bot)

        val bots = database.hodlBotDao().observeAll().first()

        assertEquals(1, bots.size)
        assertEquals(bot.type, bots[0].type)
        assertEquals(bot.status, bots[0].status)
    }

    @Test
    fun insertMultipleBotAndGetBot() = runTest {

        val bot1 = HodlBot(
            type = BotType.KNIFE_CATCH,
            status = BotStatus.RUNNING
        )

        val bot2 = HodlBot(
            type = BotType.KNIFE_CATCH,
            status = BotStatus.RUNNING
        )

        database.hodlBotDao().upsert(bot1)
        database.hodlBotDao().upsert(bot2)
        val bots = database.hodlBotDao().observeAll().first()

        assertEquals(2, bots.size)
    }

    @Test
    fun upsertAllAndGetBot() = runTest {

        val bot1 = HodlBot(
            type = BotType.KNIFE_CATCH,
            status = BotStatus.RUNNING
        )

        val bot2 = HodlBot(
            type = BotType.KNIFE_CATCH,
            status = BotStatus.RUNNING
        )

        database.hodlBotDao().upsert(bot1)
        database.hodlBotDao().upsert(bot2)
        val bots = database.hodlBotDao().observeAll().first()
        assertEquals(2, bots.size)

        database.hodlBotDao().upsertAll(bots)
        val allBots = database.hodlBotDao().observeAll().first()
        assertEquals(2, allBots.size)

        val updateBots = listOf(allBots[0].copy(status = BotStatus.STOPPED), allBots[1].copy(type = BotType.SPOT_GRID))
        database.hodlBotDao().upsertAll(updateBots)

        val bots1 = database.hodlBotDao().observeAll().first()
        assertEquals(updateBots.size, bots1.size)
        assertEquals(updateBots, bots1)

    }

}

