package com.example.trashsureapp.ui.register


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trashsureapp.R
import com.example.trashsureapp.databinding.ActivityRegisterBinding
import com.example.trashsureapp.ui.login.LoginActivity

class RegisterUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        binding.txtLogin.setOnClickListener {
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//
//        }

    }
}