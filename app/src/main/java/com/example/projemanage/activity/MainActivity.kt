package com.example.projemanage.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.projemanage.R
import com.example.projemanage.databinding.ActivityMainBinding
import com.example.projemanage.databinding.AppBarMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarBinding: AppBarMainBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()

        binding.navView.setNavigationItemSelectedListener(this)

//        setSupportActionBar(binding?.toolbarBMI)
//
//
//        if (supportActionBar != null) {
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            supportActionBar?.title = getString(R.string.BmiBarTitle)
//        }
    }

    private fun setupActionBar() {
        appBarBinding = AppBarMainBinding.inflate(layoutInflater)
        setSupportActionBar(appBarBinding.toolbarMainActivity)
        appBarBinding.toolbarMainActivity.setNavigationIcon(R.drawable.ic_action_navigataion_menu)

        appBarBinding.toolbarMainActivity.setNavigationOnClickListener {
            // Toggle 設定
            toggleDrawer()
        }


    }

    private fun toggleDrawer() {
        if (binding.drawLayout.isDrawerOpen(GravityCompat.START)) {
            // 開いた時にする処理
            binding.drawLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawLayout.openDrawer(GravityCompat.START)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        if (binding.drawLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawLayout.closeDrawer(GravityCompat.START)
        }
        doubleBackToExit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_my_profile -> {
                Toast.makeText(
                    this, "my profile",
                    Toast.LENGTH_SHORT).show()
            }
            R.id.nav_sign_out -> {
                FirebaseAuth.getInstance().signOut()

                var intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                )
                startActivity(intent)
                finish()
            }
        }
        binding.drawLayout.closeDrawer(GravityCompat.START)
        return true
    }
}