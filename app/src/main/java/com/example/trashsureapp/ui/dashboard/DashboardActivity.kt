package com.example.trashsureapp.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.trashsureapp.R
import com.example.trashsureapp.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    lateinit var fullNameUser : TextView
    lateinit var emailUser :TextView
    lateinit var btnLogout :Button

    val firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
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
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity() //Closes all activity in the application stack
    }

}