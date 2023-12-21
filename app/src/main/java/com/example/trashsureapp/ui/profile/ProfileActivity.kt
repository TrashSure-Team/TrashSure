package com.example.trashsureapp.ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.trashsureapp.R
import com.example.trashsureapp.ui.login.LoginActivity
import com.example.trashsureapp.ui.main.MainActivity
import com.example.trashsureapp.ui.settings.SettingsActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    val firebaseAuth = FirebaseAuth.getInstance()
    lateinit var btnHomeProfile: ImageView
    lateinit var btnSettingProfile: ImageView
    lateinit var btnBackProfile: ImageView
    lateinit var txtUserName: TextView
    lateinit var txtEmailUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btnHomeProfile = findViewById(R.id.btnHomeProfile)
        btnSettingProfile = findViewById(R.id.btnSettingProfile)
        btnBackProfile = findViewById(R.id.imageBackProfile)
        txtUserName = findViewById(R.id.txtnameUserProfile)
        txtEmailUser = findViewById(R.id.txtEmailUserProfile)

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            txtUserName.text = firebaseUser.displayName
            txtEmailUser.text = firebaseUser.email
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnHomeProfile.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btnSettingProfile.setOnClickListener {
            startActivity(Intent(this,SettingsActivity::class.java))
            finish()
        }
        btnBackProfile.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}