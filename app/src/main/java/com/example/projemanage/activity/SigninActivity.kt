package com.example.projemanage.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.projemanage.R
import com.example.projemanage.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth

class SigninActivity : BaseActivity() {
    private var binding:ActivitySigninBinding? = null
    private lateinit var auth: FirebaseAuth
    private val TAG:String = localClassName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        setupActionBar()

        binding?.toolbarSignIn?.setNavigationOnClickListener {
            finish()
        }
        binding?.btnSignUp?.setOnClickListener {
            registerUser()
        }
    }

    private fun setupActionBar() {
        setSupportActionBar(binding?.toolbarSignIn)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back)
        }
    }

    private fun registerUser() {
        val email: String = binding?.etEmail?.text.toString().trim { it <= ' ' }
        val password: String = binding?.etPassword?.text.toString().trim { it <= ' ' }

        if (validateForm(email, password)) {
            showProgressDialog(resources.getString(R.string.load_dialog_message))
            // TODO ログイン処理を追加する
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                hideProgressDialog()
                if (task.isSuccessful) {
                    // ログイン成功時処理
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    // ログイン失敗時、トーストで表示する
                    Log.w(TAG, "signInWithEmail:fail", task.exception)
                    Toast.makeText(
                        this,"Authentication failed.", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        } else {
            // TODO 入力不備時のダイアログを表示する
        }
    }

    private fun validateForm(email:String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(email) -> {
                showErrorSnackBar("EMailを入れてください")
                false
            }
            TextUtils.isEmpty(password) -> {
                showErrorSnackBar("passwordを入れてください")
                false
            }
            else -> { true }
        }
    }
}