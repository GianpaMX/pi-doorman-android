package io.github.gianpamx.pidoorman.domain

import io.reactivex.Single
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter


private val utc = ZoneId.of("Z")

class RingBell(
    private val timeGateway: TimeGateway,
    private val notificationGateway: NotificationGateway,
    private val formatter: DateTimeFormatter
) {
    private data class NotificationData(val time: String, val difference: String)

    operator fun invoke(serverTime: Long) =
        Single.fromCallable {
            val difference = formatDateTime(timeGateway.utcInMillis() - serverTime, utc)
            val time = formatDateTime(serverTime, timeGateway.systemZoneId())
            return@fromCallable NotificationData(time, difference)
        }.flatMapCompletable {
            notificationGateway.ringNotification(it.time, it.difference)
        }

    private fun formatDateTime(serverTime: Long, zoneId: ZoneId): String {
        val instant: Instant = Instant.ofEpochMilli(serverTime)
        val zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId)
        return formatter.format(zonedDateTime)
    }
}
