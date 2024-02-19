package com.finance.hodl.exchange.bithumb

import android.util.Log
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
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

    Log.d("SUV", "parameter String = $paramString")

    val signatureString = "$endpoint;$paramString;$nonce"

    Log.d("SUV", "signature String = $signatureString")

    val signature = PrivateApiUtil.asHex(PrivateApiUtil.hmacSha512(signatureString, apiSecret))

    return mapOf(
        "api-client-type" to "2",
        "Api-Key" to apiKey,
        "Api-Sign" to signature,
        "Api-Nonce" to nonce,
        "params" to paramString
    )
}