package io.github.gianpamx.pidoorman.boot

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import io.github.gianpamx.pidoorman.foreground.ForegroundService

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action && context != null) {
            val foregroundService = Intent(context, ForegroundService::class.java)
            ContextCompat.startForegroundService(context, foregroundService)
        }
    }
}
