package com.example.feature_login.data.base

import android.content.Context
import com.example.feature_login.data.model.AuthDateUser
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFirebaseAuth {
    private lateinit var authS: FirebaseAuth
    private lateinit var auth: FirebaseAuth

     /*fun signIn(user: AuthDateUser) {
         auth= Firebase.auth
          auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener(this,
            OnCompleteListener {  })
         auth.signInWithEmailAndPassword(user.email, user.password).addOnCompleteListener(context) { task ->
                 if (task.isSuccessful) {
                     // Sign in success, update UI with the signed-in user's information
                     Log.d(TAG, "signInWithEmail:success")
                     val user = auth.currentUser

                 } else {
                     // If sign in fails, display a message to the user.
                     Log.w(TAG, "signInWithEmail:failure", task.exception)
                     Toast.makeText(
                         baseContext, "Authentication failed.",
                     ).show()

                 }
             }
     }*/
}
