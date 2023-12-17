package com.example.trashsureapp.ui.main

import android.util.Log
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.trashsureapp.R
import com.example.trashsureapp.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    lateinit var fullNameUser : TextView
    lateinit var emailUser :TextView
    lateinit var btnLogout :ImageView

    val firebaseAuth = FirebaseAuth.getInstance()
    val mUser = FirebaseAuth.getInstance().getCurrentUser();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fullNameUser=findViewById(R.id.fullnameuser)
        emailUser=findViewById(R.id.emailuser)
        btnLogout=findViewById(R.id.btnLogoutDashboard)

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser!=null){
            fullNameUser.text=firebaseUser.displayName
            emailUser.text=firebaseUser.email
        }else{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        mUser!!.getIdToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val idToken = task.result.token
                    Log.d("IDToken", "Token: $idToken")
                    // Send token to your backend via HTTPS
                    // ...
                } else {
                    // Handle error -> task.getException();
                }
            }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity() //Closes all activity in the application stack
    }

}