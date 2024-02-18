package com.finance.hodl.exchange.bithumb

import kotlinx.serialization.Serializable
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import java.net.URLEncoder
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

object PrivateApiUtil {
    fun encodeURIComponent(s: String): String =
        URLEncoder.encode(s, "UTF-8")
            .replace("+", "%20")
            .replace("%21", "!")
            .replace("%27", "'")
            .replace("%28", "(")
            .replace("%29", ")")
            .replace("%26", "&")
            .replace("%3D", "=")
            .replace("%7E", "~")

    fun hmacSha512(value: String, key: String): ByteArray {
        val hmacSha512 = "HmacSHA512"
        val keySpec = SecretKeySpec(key.toByteArray(Charsets.UTF_8), hmacSha512)

        return Mac.getInstance(hmacSha512).apply {
            init(keySpec)
        }.doFinal(value.toByteArray(Charsets.UTF_8))
    }

    fun asHex(bytes: ByteArray): String = String(Base64.encodeBase64(Hex().encode(bytes)))
}

fun getApiHeaders(
    endpoint: String,
    params: Map<String, String>,
    apiKey: String,
    apiSecret: String
): Map<String, String> {
    val nonce = System.currentTimeMillis().toString()
    val paramString =
        params.map { "${it.key}=${PrivateApiUtil.encodeURIComponent(it.value)}" }.joinToString("&")
    val signatureString = "$endpoint;$paramString;$nonce"

    val signature = PrivateApiUtil.asHex(PrivateApiUtil.hmacSha512(signatureString, apiSecret))

    return mapOf(
        "Api-Key" to apiKey,
        "Api-Sign" to signature,
        "Api-Nonce" to nonce
    )
}

//suspend fun callApi() {
//    val apiService = /* create your Retrofit service instance */
//    val params = mapOf("order_currency" to "BTC", "payment_currency" to "KRW")
//    val headers = getApiHeaders("/trade/cancel", params, "your_api_key", "your_api_secret")
//
//    val response = BithumbTradeApiService.cancelTrade(
//        apiKey = headers["Api-Key"]!!,
//        apiSign = headers["Api-Sign"]!!,
//        apiNonce = headers["Api-Nonce"]!!,
//        body = /* create your request body */
//    )
//
//    // Handle the response
//}


@Serializable
internal data class CancelTradeRequest(
    val type: String,
    val order_id: String,
    val order_currency: String,
    val payment_currency: String
)

@Serializable
internal data class CancelTradeResponse(
    val status: String,
)


@Serializable
internal data class TradeRequest(
    val order_currency: String,
    val payment_currency: String,
    val units: Float,
    val price: Int,
    val type: String
)

@Serializable
internal data class TradeResponse(
    val status: String,
    val order_id: String
)

internal interface BithumbTradeApiService {
    @POST("trade/place")
    @Headers("Accept: application/json", "Content-Type: application/x-www-form-urlencoded")
    suspend fun placeTrade(
        @Body tradeRequest: TradeRequest,
        @Header("Api-Key") apiKey: String,
        @Header("Api-Nonce") apiNonce: String,
        @Header("Api-Sign") apiSign: String,
    ): TradeResponse

    @POST("trade/cancel")
    @Headers("Accept: application/json", "Content-Type: application/x-www-form-urlencoded")
    suspend fun cancelTrade(
        @Body request: CancelTradeRequest,
        @Header("Api-Key") apiKey: String,
        @Header("Api-Nonce") apiNonce: String,
        @Header("Api-Sign") apiSign: String,
    ): Response<Void> // Adjust the response type based on actual API response
}
