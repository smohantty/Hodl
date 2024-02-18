package com.finance.hodl.data.repository

import com.finance.hodl.database.dao.BotDao
import com.finance.hodl.database.dao.TransactionDao
import javax.inject.Inject

class BotRepository @Inject constructor(
    private val botDao: BotDao,
    private val transactionDao: TransactionDao
) {

}