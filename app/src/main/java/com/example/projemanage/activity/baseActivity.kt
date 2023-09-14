package com.example.projemanage.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projemanage.R

class baseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}