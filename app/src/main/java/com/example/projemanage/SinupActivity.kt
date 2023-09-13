package com.example.projemanage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projemanage.databinding.ActivitySinupBinding

class SinupActivity : AppCompatActivity() {
    private var binding:ActivitySinupBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySinupBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setupActionBar()
    }

    private fun setupActionBar() {
        setSupportActionBar(binding?.toolbarSignUp)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back)
        }

        binding?.toolbarSignUp?.setNavigationOnClickListener {
            finish()
        }
    }
}