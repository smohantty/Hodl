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
import java.math.BigDecimal

class HodlBotDaoTest {

    private lateinit var database: HodlDatabase

    fun Bot(): HodlBot {
        return HodlBot(
            id = "id1",
            type = BotType.KNIFE_CATCH,
            ticker = "BTC",
            currency = "KRW",
            low = BigDecimal(100),
            high = BigDecimal(100),
            girdCount = 10,
            status = BotStatus.CREATED
        )
    }

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            HodlDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @Test
    fun insertBotAndGetBot() = runTest {

        val bot = Bot()

        database.hodlBotDao().upsert(bot)

        val bots = database.hodlBotDao().observeAll().first()

        assertEquals(1, bots.size)
        assertEquals(bot, bots[0])
    }

    @Test
    fun insertMultipleBotAndGetBot() = runTest {

        val bot1 = Bot()
        val bot2 = bot1.copy(id = "id2")

        database.hodlBotDao().upsert(bot1)
        database.hodlBotDao().upsert(bot2)
        val bots = database.hodlBotDao().observeAll().first()

        assertEquals(2, bots.size)
    }

    @Test
    fun upsertAllAndGetBot() = runTest {

        val bot = Bot()
        val botList = listOf(bot, bot.copy(id = "bot2"))

        database.hodlBotDao().upsertAll(botList)

        val bots = database.hodlBotDao().observeAll().first()
        assertEquals(2, bots.size)
        assertEquals(botList, bots)


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

