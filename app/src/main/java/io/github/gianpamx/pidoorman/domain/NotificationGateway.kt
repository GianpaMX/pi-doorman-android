package io.github.gianpamx.pidoorman.domain

import io.reactivex.Completable

interface NotificationGateway {
    fun ringNotification(time: String, difference: String): Completable
}
