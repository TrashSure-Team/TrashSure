package com.example.trashsureapp.ui.register

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.trashsureapp.databinding.ActivityRegisterBinding
import com.example.trashsureapp.ui.login.LoginActivity
import com.example.trashsureapp.R
import com.example.trashsureapp.ui.dashboard.DashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    lateinit var editFullName: EditText
    lateinit var editEmail: EditText
    lateinit var editPassword: EditText
    lateinit var editReenterPass: EditText
    lateinit var btnRegister :Button
    lateinit var progressDialog: ProgressDialog

    var firebaseAuth = FirebaseAuth.getInstance()

    //Function to check , whether user data exists or not !
    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        editFullName = findViewById(R.id.txtFullName)
        editEmail = findViewById(R.id.txtYourEmail)
        editPassword = findViewById(R.id.txtPasswordFirst)
        editReenterPass = findViewById(R.id.txtReenterPassword)
        btnRegister = findViewById(R.id.ButtonRegister)

        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Waiting")
        progressDialog.setMessage("Wait a few seconds...")


        //register button function
        btnRegister.setOnClickListener {
            if (editFullName.text.isNotEmpty() && editEmail.text.isNotEmpty() && editPassword.text.isNotEmpty()) {
                if (editPassword.text.toString() == editReenterPass.text.toString()) {
                    // REGISTER ACTIVE
                    processRegister()
                }else{
                    Toast.makeText(this,"Your password is not the same, please check again",LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Please fill in the data correctly first",LENGTH_SHORT).show()
            }
        }
    }

    private fun processRegister(){
        val fullname = editFullName.text.toString()
        val email = editEmail.text.toString()
        val password = editPassword.text.toString()

        progressDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email ,password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    val userUpdateProfile = userProfileChangeRequest {
                        displayName = fullname
                    }
                    val user=task.result.user
                    user!!.updateProfile(userUpdateProfile)
                        .addOnCompleteListener() {
                            progressDialog.dismiss()
                            startActivity(Intent(this,DashboardActivity::class.java))
                        }
                        .addOnFailureListener{error2->
                            Toast.makeText(this,error2.localizedMessage, LENGTH_SHORT).show()
                        }
                }
            }
            .addOnFailureListener{ error->
                Toast.makeText(this,error.localizedMessage, LENGTH_SHORT).show()
            }
    }
}