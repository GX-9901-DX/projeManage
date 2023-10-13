package com.example.projemanage.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import com.example.projemanage.R
import com.example.projemanage.activity.IntroActivity
import com.example.projemanage.firebase.fireStoreClass

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        @Suppress("deprecation")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        // TODO フリーのフォントを使ってみよう
//        val typeface:Typeface = Typeface.createFromAsset(assets, "")
//        val tv_app_name: TextView = findViewById(R.id.tv_app_name)
//        tv_app_name.typeface =typeface

        Handler(Looper.getMainLooper()).postDelayed({
            val currentUserID = fireStoreClass().getCurrentUserId()

            if (currentUserID.isNotEmpty()) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, IntroActivity::class.java))
            }

            finish()
        }, 2500)
    }
}