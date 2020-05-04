package io.github.gianpamx.pidoorman.app

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import org.threeten.bp.format.DateTimeFormatter

@Module
class AppModule {
    @Provides
    fun provideContext(app: Application): Context = app

    @Provides
    fun provideDateTimeFormatter(): DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_TIME
}
