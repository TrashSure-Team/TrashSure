package com.example.trashsureapp.ui.main

import android.util.Log
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.trashsureapp.R
import com.example.trashsureapp.feature.classification.ClassificationActivity
import com.example.trashsureapp.ui.login.LoginActivity
import com.example.trashsureapp.ui.maps.MapsActivity
import com.example.trashsureapp.ui.profile.ProfileActivity
import com.example.trashsureapp.ui.settings.SettingsActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private val CAMERA_PERMISSION_CODE = 101
    private val CAMERA_REQUEST_CODE = 102
    lateinit var fullNameUser : TextView
    lateinit var emailUser :TextView
    lateinit var btnLogout :ImageView
    lateinit var btnClassification:ImageView
    lateinit var btnObject: ImageView
    private val REQUEST_IMAGE_CAPTURE = 101
    lateinit var btnExchange:ImageView
    lateinit var btnSettingHome:ImageView
    lateinit var btnProfileHome :ImageView

    val firebaseAuth = FirebaseAuth.getInstance()
    val mUser = FirebaseAuth.getInstance().getCurrentUser();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fullNameUser=findViewById(R.id.fullnameuser)
        emailUser=findViewById(R.id.emailuser)
        btnLogout=findViewById(R.id.btnLogoutDashboard)
        btnClassification=findViewById(R.id.btnClassification)
        btnObject=findViewById(R.id.btnObject)
        btnExchange=findViewById(R.id.btnExchange)
        btnSettingHome=findViewById(R.id.btnSettingHome)
        btnProfileHome=findViewById(R.id.btnProfileHome)

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser!=null){
            fullNameUser.text=firebaseUser.displayName
            emailUser.text=firebaseUser.email
        }else{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        btnProfileHome.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
            finish()
        }
        btnLogout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        btnClassification.setOnClickListener {
            startActivity(Intent(this, ClassificationActivity::class.java))
        }
        btnObject.setOnClickListener {
            dispatchTakePictureIntern()
        }
        btnExchange.setOnClickListener {
            startActivity(Intent(this,MapsActivity::class.java))

        }
        btnSettingHome.setOnClickListener {
            startActivity(Intent(this,SettingsActivity::class.java))
            finish()
        }
        btnSettingHome.setOnClickListener {
            startActivity(Intent(this,SettingsActivity::class.java))
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

    private fun dispatchTakePictureIntern() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity() //Closes all activity in the application stack
    }

}