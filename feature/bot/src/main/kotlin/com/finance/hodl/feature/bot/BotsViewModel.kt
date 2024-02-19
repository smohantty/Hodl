
package com.finance.hodl.feature.bot

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.finance.hodl.data.repository.ExchangeRepository
import com.finance.hodl.domain.BotsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import src.main.com.finance.hodl.model.data.LimitOrder
import src.main.com.finance.hodl.model.data.OrderType
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class BotsViewModel @Inject constructor(
    private val botUseCase: BotsUseCase,
    private val exchange: ExchangeRepository,
) : ViewModel() {

    fun observePrice(ticker: String, currency:String): Flow<BigDecimal> = flow {
        val limitOrder = LimitOrder(
            OrderType.BUY,
            "BTC",
            "KRW",
            BigDecimal(10000),
            BigDecimal(70400000)
        )
        while (true) {
            Log.d("Suv", "Before Request")
            emit(exchange.getPrice(ticker, currency))
            exchange.placeLimitOrder(limitOrder)
            delay(10000)
        }
    }
}