
package com.finance.hodl.domain

import com.finance.hodl.data.repository.BotRepository
import com.finance.hodl.data.repository.ExchangeRepository
import javax.inject.Inject

class BotsUseCase @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
    private val userDataRepository: BotRepository,
) {

}