@file:UseSerializers(BigDecimalAsPlainStringSerializer::class, BigIntegerAsPlainStringSerializer::class)
package com.finance.hodl.data.source.network

import com.finance.hodl.lib.BigDecimalAsPlainStringSerializer
import com.finance.hodl.lib.BigIntegerAsPlainStringSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.math.BigDecimal
import java.math.BigInteger
import java.util.Currency

@Serializable
sealed class OrderType {
    data object Buy : OrderType()
    data object Sell : OrderType()
}

@Serializable
data class Crypto(
    val ticker: String,
    val currency: String,
)

@Serializable
data class CryptoPrice(
    val crypto: Crypto,
    val price: BigDecimal,
    val timestamp: BigInteger,
)

@Serializable
data class CryptoOrder(
    val crypto: Crypto,
    val price: BigDecimal,
    val timestamp: BigInteger,
    val orderType: OrderType,
)
