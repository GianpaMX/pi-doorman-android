package io.github.gianpamx.pidoorman.app

import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.gianpamx.pidoorman.domain.NotificationGateway
import io.github.gianpamx.pidoorman.domain.TimeGateway
import io.github.gianpamx.pidoorman.gateway.AndroidNotificationGateway
import org.threeten.bp.Clock
import org.threeten.bp.ZoneId

@Module
class GatewayModule {
    @Provides
    fun provideTimeGateway(): TimeGateway = object : TimeGateway {
        override fun utcInMillis() = Clock.systemUTC().millis()
        override fun systemZoneId() = ZoneId.systemDefault()
    }

    @Provides
    fun provideNotificationGateway(context: Context): NotificationGateway =
        AndroidNotificationGateway(context)
}
