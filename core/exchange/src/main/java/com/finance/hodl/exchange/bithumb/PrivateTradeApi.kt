package com.finance.hodl.exchange.bithumb

import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

@Serializable
internal data class CancelTradeResponse(
    val status: String,
)


@Serializable
internal data class TradeResponse(
    val status: String,
    val order_id: String
)

internal interface PrivateTradeApiService {

    @POST("trade/place")
    @Headers("Accept: application/json", "Content-Type: application/x-www-form-urlencoded")
    suspend fun placeTrade(
        @Body tradeRequestParams: String,
        @Header("Api-Key") apiKey: String,
        @Header("Api-Nonce") apiNonce: String,
        @Header("Api-Sign") apiSign: String,
        @Header("api-client-type") clientType: String = "2",
    ): TradeResponse

    @POST("trade/cancel")
    @Headers("Accept: application/json", "Content-Type: application/x-www-form-urlencoded")
    suspend fun cancelTrade(
        @Body tradeCancelParams: String,
        @Header("Api-Key") apiKey: String,
        @Header("Api-Nonce") apiNonce: String,
        @Header("Api-Sign") apiSign: String,
        @Header("api-client-type") clientType: String = "2",
    ): CancelTradeResponse
}
