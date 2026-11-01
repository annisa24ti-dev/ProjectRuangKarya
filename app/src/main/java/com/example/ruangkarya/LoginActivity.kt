package com.example.ruangkarya

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ruangkarya.databinding.LoginMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = LoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )

            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString()

            if (username.isNotEmpty() && username == password) {

                val sharedPref = getSharedPreferences(
                    "user_pref",
                    MODE_PRIVATE
                )

                sharedPref.edit()
                    .putBoolean("isLogin", true)
                    .putString("username", username)
                    .apply()

                Toast.makeText(
                    this,
                    "Login berhasil! Username: $username",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(Intent(this, BaseActivity::class.java))
                finish()

            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Username dan password tidak sesuai.")
                    .setPositiveButton("OK", null)
                    .show()

                Toast.makeText(
                    this,
                    "Login gagal, periksa username dan password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}