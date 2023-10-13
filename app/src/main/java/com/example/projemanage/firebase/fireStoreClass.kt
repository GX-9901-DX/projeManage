package com.example.projemanage.firebase

import android.util.Log
import com.example.projemanage.activity.SigninActivity
import com.example.projemanage.activity.SinupActivity
import com.example.projemanage.model.User
import com.example.projemanage.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class fireStoreClass {
    private val mFirebase = FirebaseFirestore.getInstance()

    fun registerUser(activity:SinupActivity, userInfo: User) {
        mFirebase.collection(Constants.USERS)
            .document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisterdSuccess()
            }
            .addOnFailureListener {
                e ->
                Log.e(activity.javaClass.simpleName, "Error write document ${e}")
            }
    }

    fun signInUser(activity: SigninActivity) {
        mFirebase.collection(Constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(User::class.java)
                loggedInUser?.let {
                    activity.signInSuccess(it)
                }

            }
            .addOnFailureListener {
                e ->
                Log.e(activity.javaClass.simpleName, "Error write document ${e}")
            }
    }

    fun getCurrentUserId():String {
        var currentUser = FirebaseAuth.getInstance().currentUser
        var currentUserID = ""
        if (currentUser != null) {
            currentUserID = currentUser.uid
        }
        return currentUserID
    }
}