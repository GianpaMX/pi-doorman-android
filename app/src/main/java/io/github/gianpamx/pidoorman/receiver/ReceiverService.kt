package io.github.gianpamx.pidoorman.receiver

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.github.gianpamx.pidoorman.app.App
import io.github.gianpamx.pidoorman.app.AppComponent
import io.github.gianpamx.pidoorman.domain.RingBell
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ReceiverService : FirebaseMessagingService() {
    private val appComponent: AppComponent by lazy { (application as App).component }

    private val component: ReceiverComponent by lazy {
        DaggerReceiverComponent.builder()
            .appComponent(appComponent)
            .service(this)
            .build()
    }

    @Inject
    lateinit var ringBell: RingBell

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun onNewToken(token: String) {
        Log.d("ReceiverService", "onNewToken: $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.data["time"]?.toLong()?.let { serverTime ->
            ringBell(serverTime).subscribe({
                Log.d("ReceiverService", "onMessageReceived:ringBell")
            }, { t ->
                Log.e("ReceiverService", "onMessageReceived", t)
            }).bindToLifecycle()
        } ?: Log.e(
            "ReceiverService",
            "onMessageReceived",
            Exception("remoteMessage.data[time] is null")
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun Disposable.bindToLifecycle() {
        compositeDisposable.add(this)
    }
}
