
package com.example.ruangkarya

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler.postDelayed({
            val sharedPref = getSharedPreferences(
                "user_pref",
                MODE_PRIVATE
            )

            val isLogin = sharedPref.getBoolean("isLogin", false)

            val tujuan = if (isLogin) {
                BaseActivity::class.java
            } else {
                LoginActivity::class.java
            }

            startActivity(Intent(this, tujuan))
            finish()
        }, 2000)
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
