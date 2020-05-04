package io.github.gianpamx.pidoorman.domain

import io.reactivex.Single
import org.threeten.bp.Instant
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

class RingBell(
    private val timeGateway: TimeGateway,
    private val notificationGateway: NotificationGateway,
    private val formatter: DateTimeFormatter
) {
    private data class NotificationData(val time: String, val difference: String)

    operator fun invoke(serverTime: Long) =
        Single.fromCallable {
            val difference = (timeGateway.utcInMillis() - serverTime).toString()
            val time = formatDateTime(serverTime)
            return@fromCallable NotificationData(time, difference)
        }.flatMapCompletable {
            notificationGateway.ringNotification(it.time, it.difference)
        }

    private fun formatDateTime(serverTime: Long): String {
        val instant: Instant = Instant.ofEpochMilli(serverTime)
        val zonedDateTime = ZonedDateTime.ofInstant(instant, timeGateway.systemZoneId())
        return formatter.format(zonedDateTime)
    }
}
