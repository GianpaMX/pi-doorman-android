package io.github.gianpamx.pidoorman.foreground

import android.app.Service
import android.content.Intent
import android.os.IBinder
import io.github.gianpamx.pidoorman.app.App
import io.github.gianpamx.pidoorman.app.AppComponent
import io.github.gianpamx.pidoorman.domain.FOREGROUND_NOTIFICATION_ID
import io.github.gianpamx.pidoorman.domain.NotificationGateway
import javax.inject.Inject

object Stop

class ForegroundService : Service() {
    private val appComponent: AppComponent by lazy { (application as App).component }

    private val component: ForegroundComponent by lazy {
        DaggerForegroundComponent.builder()
            .appComponent(appComponent)
            .service(this)
            .build()
    }

    @Inject
    lateinit var notificationGateway: NotificationGateway

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startForeground(FOREGROUND_NOTIFICATION_ID, notificationGateway.foregroundNotification())
        return START_STICKY
    }
}
