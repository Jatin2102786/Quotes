package com.jatin.quotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash)

            Handler().postDelayed({

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                finish()
            }, SPLASH_TIME_OUT)
    }
}