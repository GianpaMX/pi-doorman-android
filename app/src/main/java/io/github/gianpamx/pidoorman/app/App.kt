package io.github.gianpamx.pidoorman.app

import android.app.Application
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.jakewharton.threetenabp.AndroidThreeTen

class App : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("App", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                Log.d("App", "token: ${task.result?.token}")
            })
    }
}
