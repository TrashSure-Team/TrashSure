package com.example.trashsureapp.ui.welcome

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.example.trashsureapp.R
import com.example.trashsureapp.databinding.ActivityWelcomeBinding
import com.example.trashsureapp.ui.login.LoginActivity
import com.example.trashsureapp.ui.register.RegisterActivity
import com.example.trashsureapp.ui.register.RegisterUserActivity


class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.buttonRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        setupView()
        playAnimation()

    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.gambartrash, View.TRANSLATION_X, -30f, 30f).apply{
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val login = ObjectAnimator.ofFloat(binding.buttonLogin, View.ALPHA, 1f).setDuration(100)
        val register = ObjectAnimator.ofFloat(binding.buttonRegister, View.ALPHA, 1f).setDuration(100)
        val title = ObjectAnimator.ofFloat(binding.txtnameApp, View.ALPHA, 1f).setDuration(100)
        val title2 =ObjectAnimator.ofFloat(binding.txtsplash1,View.ALPHA,1f).setDuration(100)
        val desc = ObjectAnimator.ofFloat(binding.txtsplash2, View.ALPHA, 1f).setDuration(100)

        val together = AnimatorSet().apply {
            playTogether(login, register)
        }
        AnimatorSet().apply {
            playSequentially(title, title2,desc, together)
            start()
        }
    }
}