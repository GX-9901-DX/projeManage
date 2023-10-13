package com.example.projemanage.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projemanage.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(binding?.toolbarBMI)
//
//
//        if (supportActionBar != null) {
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            supportActionBar?.title = getString(R.string.BmiBarTitle)
//        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        doubleBackToExit()
    }
}