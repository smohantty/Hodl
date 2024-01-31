package com.finance.hodl

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.finance.hodl.services.HodleServiceHandle
import dagger.hilt.android.HiltAndroidApp


//suspend fun testRetroApi()
//{
//    val apiService = BithumbPublicApiService.create()
//    try {
//        val obj = apiService.getTicker("BTC", "KRW")
//        Log.d("SUV", "Retro response object : $obj")
//    } catch (e: Exception) {
//        Log.d("SUV","Exception: ${e.message}")
//    }
//}
//
//suspend fun testLastTradedPriceApi()
//{
//    val apiService = BithumbPublicApiService.create()
//    try {
//        val obj = apiService.getRecentTransactionsForTicker("BTC", "KRW")
//        Log.d("SUV", "Last Btc traded price  : ${obj.data[0].price}")
//    } catch (e: Exception) {
//        Log.d("SUV","Exception: ${e.message}")
//    }
//}

//fun testHttpApi()
//{
//    val client = OkHttpClient()
//
//    val request = Request.Builder()
//        .url("https://api.bithumb.com/public/orderbook/BTC_KRW")
//        .get()
//        .addHeader("accept", "application/json")
//        .build()
//    try {
//        // Execute the request
//        val response = client.newCall(request).execute()
//
//        if (response.isSuccessful) {
//            // Parse JSON response using Gson
//            val gson = Gson()
//            val responseBodyString = response.body()?.string()
//
//            Log.d("SUV", "json String : $responseBodyString")
//
//            //val tickerResponse = gson.fromJson(responseBodyString, BithumbSingleTickerResponse::class.java)
//            //Log.d("SUV", "data obj : $tickerResponse")
//            // Add more fields as needed
//
//        } else {
//            Log.d("SUV","Error: ${response.code()} ${response.message()}")
//        }
//    } catch (e: IOException) {
//        Log.d("SUV","Exception: ${e.message}")
//    }
//}
@HiltAndroidApp
class HodlApplication : Application() {
    private lateinit var serviceHandle: HodleServiceHandle
    override fun onCreate() {
        super.onCreate()
    }
    private fun createNotificationChannel(context: Context, channelId: String, channelName: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDescription
            }
            // Register the channel with the system; you can't change the importance or other notification behaviors after this
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
