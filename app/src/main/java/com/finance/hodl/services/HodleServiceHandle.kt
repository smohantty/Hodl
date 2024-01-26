package com.finance.hodl.services

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CompletableDeferred

class HodleServiceHandle(private val context: Context) {
    private lateinit var hodlService: HodlService
    private var serviceConnected = CompletableDeferred<Boolean>()
    private var connected: Boolean = false;

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as HodlService.HodlServiceBinder
            hodlService = binder.getService()
            serviceConnected.complete(true)
            //hodlService.pubFunction("hello")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            connected = false
        }
    }

    val isConnected:Boolean get() = connected

    suspend fun connect(): Boolean {
        val hodlServiceIntent = Intent(context, HodlService::class.java)
        context.bindService(hodlServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE)
        connected = true;
        return serviceConnected.await()
    }
}