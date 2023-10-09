package com.example.projemanage.firebase

import com.example.projemanage.activity.SinupActivity
import com.example.projemanage.model.User
import com.example.projemanage.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class fireStoreClass {
    private val mFirebase = FirebaseFirestore.getInstance()

    fun registerUser(activity:SinupActivity, userInfo: User) {
        mFirebase.collection(Constants.USERS).document().set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                activity.userRegisterdSuccess()
            }
    }

    fun getCurrentUserId():String {

        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}