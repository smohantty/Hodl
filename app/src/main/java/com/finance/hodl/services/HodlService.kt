package com.finance.hodl.services

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.provider.Settings.Global
import android.util.Log
import androidx.core.app.NotificationCompat
import com.finance.hodl.R
import com.finance.hodl.activities.HodlMainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HodlService : Service() {
    private val binder = HodlServiceBinder()

    inner class HodlServiceBinder : Binder() {
        fun getService(): HodlService {
            return this@HodlService
        }
    }

    companion object {
        private const val TAG = "SUV"
        private const val NOTIFICATION_ID = 101
    }

    private fun createNotification(): Notification {
        val notificationIntent = Intent(this, HodlMainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, "HodlChannelId")
            .setContentTitle("Hodl Service")
            .setContentText("Running")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "HodlService::onCreate")
    }

    private fun runService() {
    }

    public fun pubFunction(str: String) {
        runService()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d(TAG, "HodlService::onCreate")
        startForeground(NOTIFICATION_ID, createNotification())
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        // This service is not designed to be bound
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
