package io.github.gianpamx.pidoorman

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import io.github.gianpamx.pidoorman.foreground.ForegroundService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ContextCompat.startForegroundService(
            this,
            Intent(this, ForegroundService::class.java)
        )
    }
}
