package io.github.gianpamx.pidoorman.gateway

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.DEFAULT_ALL
import androidx.core.app.NotificationCompat.PRIORITY_MAX
import androidx.core.app.NotificationManagerCompat
import io.github.gianpamx.pidoorman.MainActivity
import io.github.gianpamx.pidoorman.R
import io.github.gianpamx.pidoorman.domain.NotificationGateway
import io.reactivex.Completable

private const val NOTIFICATION_ID = 1

class AndroidNotificationGateway(private val context: Context) : NotificationGateway {
    override fun ringNotification(time: String, difference: String) = Completable.fromCallable {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createNotificationChannel()

        with(context) {
            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            val notification = NotificationCompat.Builder(this, getString(R.string.ring_channel_id))
                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text, time, difference))
                .setContentIntent(pendingIntent)
                .setPriority(PRIORITY_MAX)
                .setDefaults(DEFAULT_ALL)
                .setVibrate(longArrayOf(100, 250, 100, 250, 100, 250))
                .setAutoCancel(true)
                .build()

            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() = with(context) {
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .createNotificationChannel(NotificationChannel(
                getString(R.string.ring_channel_id),
                getString(R.string.ring_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = getString(R.string.ring_channel_description)
            })
    }
}
