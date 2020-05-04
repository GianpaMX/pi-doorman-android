package io.github.gianpamx.pidoorman.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import io.github.gianpamx.pidoorman.domain.RingBell
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, GatewayModule::class])
interface AppComponent {

    val application: Application

    val ringBell: RingBell

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}
