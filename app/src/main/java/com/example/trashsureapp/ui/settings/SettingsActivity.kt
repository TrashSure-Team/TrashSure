package com.example.trashsureapp.ui.settings

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.trashsureapp.R
import com.example.trashsureapp.ui.login.LoginActivity
import com.example.trashsureapp.ui.main.MainActivity
import com.example.trashsureapp.ui.profile.ProfileActivity
import com.google.firebase.auth.FirebaseAuth

class SettingsActivity : AppCompatActivity() {

    lateinit var btnBackHome :ImageView
    lateinit var btnHomeSetting:ImageView
    lateinit var btnProfileSetting:ImageView
    lateinit var btnLogoutSettings : Button

    val firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        btnBackHome=findViewById(R.id.btnBackSettings)
        btnHomeSetting=findViewById(R.id.btnHomeSettings)
        btnProfileSetting=findViewById(R.id.btnProfileSettings)
        btnLogoutSettings=findViewById(R.id.buttonToLogout)


        btnBackHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btnHomeSetting.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

        btnProfileSetting.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
            finish()
        }

        btnLogoutSettings.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}