package com.crypto.exchange

sealed class Exchange(val name: String) {
    object Bithumb : Exchange("Bithumb")
    object Binance : Exchange("Binance")
    object Mock : Exchange("Mock")
}


class CryptoExchange private constructor(private val builder: Builder) {

    // Test Api
    fun name():String {
        return builder.exchange.name
    }

    class Builder {
        internal var exchange: Exchange = Exchange.Mock
        internal var apiKey: String = String()
        internal var privateKey: String = String()

        fun apiKey(value: String) = apply { this.apiKey = value }
        fun privateKey(value: String) = apply { this.privateKey = value }
        fun exchange(value: Exchange) = apply { this.exchange = value }
        fun build() = CryptoExchange(this)
    }

    companion object {
        // Builder function
        fun builder() = Builder()
    }
}