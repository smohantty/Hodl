package com.finance.hodl.exchange.bithumb

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import okhttp3.RequestBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded

internal interface BithumbAccountApiService {
    @POST("info/balance")
    @FormUrlEncoded
    @Headers("Accept: application/json", "Content-Type: application/x-www-form-urlencoded")
    suspend fun getBalance(
        @Field("currency") currency: String,
        @Header("Api-Key") apiKey: String,
        @Header("Api-Nonce") apiNonce: String,
        @Header("Api-Sign") apiSign: String
    ): NetworkResponse<BalanceInfo> // Assume BalanceResponse is your data model class

    @POST("info/account")
    @Headers("Accept: application/json", "Content-Type: application/x-www-form-urlencoded")
    suspend fun getAccountInfo(
        @Header("Api-Key") apiKey: String,
        @Header("Api-Nonce") apiNonce: String,
        @Header("Api-Sign") apiSign: String,
        @Body body: RequestBody = RequestBody.create(null, ByteArray(0)) // Empty body
    ): NetworkResponse<AccountInfo>
}
