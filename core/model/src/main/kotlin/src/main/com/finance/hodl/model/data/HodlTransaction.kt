package src.main.com.finance.hodl.model.data

import java.math.BigDecimal
import java.util.Currency

data class HodlBotTransaction (
    val id: String,
)

enum class OrderType {
    BUY,
    SELL
}

data class LimitOrder(
    val type: OrderType,
    val ticker: String,
    val paymentCurrency: String,
    val paymentAmount: BigDecimal,
    val limitPrice: BigDecimal,
) {

}

data class OrderInfo(
    val type: OrderType,
    val ticker: String,
    val paymentCurrency: String,
    val paymentAmount: BigDecimal,
    val limitPrice: BigDecimal,
) {

}

