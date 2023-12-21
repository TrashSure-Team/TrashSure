package com.example.trashsureapp.ui.login

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.trashsureapp.R
import com.example.trashsureapp.databinding.ActivityLoginBinding
import com.example.trashsureapp.ui.main.MainActivity
import com.example.trashsureapp.ui.register.RegisterActivity
import com.example.trashsureapp.ui.welcome.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var btnLogin: Button
    lateinit var progressDialog: ProgressDialog

    var firebaseAuth = FirebaseAuth.getInstance()

    //Function to check , whether user data exists or not !
    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editEmail = findViewById(R.id.LoginEmail)
        editPassword = findViewById(R.id.LoginPassword)
        btnLogin = findViewById(R.id.LoginButtonLogin)

        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Waiting")
        progressDialog.setMessage("Wait a few seconds...")

        binding.txtsignup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class .java)
            startActivity(intent)
            finish()
        }
        binding.btnBackLogin.setOnClickListener {
            val intent= Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener {
            if(editEmail.text.isNotEmpty()&&editPassword.text.isNotEmpty()){
                loginProcess()
            }else{
                Toast.makeText(this,"Data is empty, please fill in your email and password first!",LENGTH_SHORT).show()
            }
        }
    }

    private fun loginProcess(){
        val email=editEmail.text.toString()
        val password=editPassword.text.toString()

        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            .addOnFailureListener { error->
                Toast.makeText(this, error.localizedMessage, LENGTH_SHORT).show()
            }
            .addOnCompleteListener{
                progressDialog.dismiss()
            }
    }
}