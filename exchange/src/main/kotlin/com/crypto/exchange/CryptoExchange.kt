package com.crypto.exchange

enum class Exchange() {
    Bithumb,
    Binance,
    Mock
}
class CryptoExchange private constructor(private val builder: Builder) {

    // Test Api
    fun name():String {
        return when(builder.exchange) {
            Exchange.Bithumb -> "Bithumb"
            Exchange.Binance -> "Binance"
            Exchange.Mock -> "Mock"
        }
    }

    class Builder {
        var exchange: Exchange = Exchange.Mock
        var apiKey: String = String()
        var privateKey: String = String()

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