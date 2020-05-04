package io.github.gianpamx.pidoorman.receiver

import dagger.BindsInstance
import dagger.Component
import io.github.gianpamx.pidoorman.app.AppComponent

@ReceiverScope
@Component(dependencies = [AppComponent::class])
interface ReceiverComponent {
    fun inject(service: ReceiverService)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun service(receiverService: ReceiverService): Builder
        fun appComponent(appComponent: AppComponent?): Builder
        fun build(): ReceiverComponent
    }
}
