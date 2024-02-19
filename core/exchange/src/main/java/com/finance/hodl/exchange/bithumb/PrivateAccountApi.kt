package com.finance.hodl.exchange.bithumb

import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface PrivateAccountApiService {
    @POST("info/balance")
    @FormUrlEncoded
    @Headers("Accept: application/json", "Content-Type: application/x-www-form-urlencoded")
    suspend fun getBalance(
        @Body balanceInfoParams: String,
        @Header("Api-Key") apiKey: String,
        @Header("Api-Nonce") apiNonce: String,
        @Header("Api-Sign") apiSign: String
    ): NetworkResponse<BalanceInfo> // Assume BalanceResponse is your data model class

    @POST("info/account")
    @Headers("Accept: application/json", "Content-Type: application/x-www-form-urlencoded")
    suspend fun getAccountInfo(
        @Body accountInfoParams: String,
        @Header("Api-Key") apiKey: String,
        @Header("Api-Nonce") apiNonce: String,
        @Header("Api-Sign") apiSign: String,
    ): NetworkResponse<AccountInfo>
}
