package io.github.gianpamx.pidoorman.domain

import android.app.Notification
import io.reactivex.Completable

const val FOREGROUND_NOTIFICATION_ID = 2

interface NotificationGateway {
    fun ringNotification(time: String, difference: String): Completable
    fun foregroundNotification(): Notification
}
