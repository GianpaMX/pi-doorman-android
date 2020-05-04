package io.github.gianpamx.pidoorman.app

import dagger.Module
import dagger.Provides
import io.github.gianpamx.pidoorman.domain.NotificationGateway
import io.github.gianpamx.pidoorman.domain.RingBell
import io.github.gianpamx.pidoorman.domain.TimeGateway
import org.threeten.bp.format.DateTimeFormatter

@Module
class DomainModule {
    @Provides
    fun provideRinBell(
        timeGateway: TimeGateway,
        notificationGateway: NotificationGateway,
        formatter: DateTimeFormatter
    ) = RingBell(timeGateway, notificationGateway, formatter)
}
