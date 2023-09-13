package com.example.projemanage

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.example.projemanage.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private var binding: ActivityIntroBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        @Suppress("deprecation")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        binding?.btnSignIn?.setOnClickListener {
            startActivity(Intent(this,SigninActivity::class.java))
        }

        binding?.btnSignUp?.setOnClickListener {
            val intent = Intent(this, SinupActivity::class.java)
            startActivity(intent)
        }

    }
}