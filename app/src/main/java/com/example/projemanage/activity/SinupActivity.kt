package com.example.projemanage.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import com.example.projemanage.R
import com.example.projemanage.databinding.ActivitySinupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SinupActivity : BaseActivity() {
    private var binding:ActivitySinupBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySinupBinding.inflate(layoutInflater)
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

        binding?.btnSignUp?.setOnClickListener {
            registerUser()
        }
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

    private fun registerUser() {
        val name:String = binding?.etName?.text.toString().trim { it <= ' ' }
        val email: String = binding?.etEmail?.text.toString().trim { it <= ' ' }
        val password: String = binding?.etPassword?.text.toString().trim { it <= ' ' }

        if (validateForm(name, email, password)) {
            showProgressDialog(getString(R.string.load_dialog_message))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                task ->
                hideProgressDialog()
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser? = task.result.user
                    val firebaseEmail = firebaseUser?.email

                    FirebaseAuth.getInstance().signOut()
                    finish()
                } else {
                    // TODO　サインアップ失敗時の Error Dialog
                }
            }
            Toast.makeText(this,"test enable register", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateForm(name:String, email:String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name) -> {
                showErrorSnackBar("名前を入れてください")
                false
            }
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