package io.github.gianpamx.pidoorman.foreground

import dagger.BindsInstance
import dagger.Component
import io.github.gianpamx.pidoorman.ServiceScope
import io.github.gianpamx.pidoorman.app.AppComponent

@ServiceScope
@Component(dependencies = [AppComponent::class])
interface ForegroundComponent {
    fun inject(service: ForegroundService)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun service(foregroundService: ForegroundService): Builder
        fun appComponent(appComponent: AppComponent?): Builder
        fun build(): ForegroundComponent
    }
}
