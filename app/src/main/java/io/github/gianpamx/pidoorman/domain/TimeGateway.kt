package io.github.gianpamx.pidoorman.domain

import org.threeten.bp.ZoneId

interface TimeGateway {
    fun utcInMillis(): Long
    fun systemZoneId(): ZoneId
}
