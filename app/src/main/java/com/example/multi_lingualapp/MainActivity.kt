package com.example.multi_lingualapp

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnChangeLanguage: Button
    private lateinit var btnChangeLanguageEnglish: Button
    private lateinit var btnChangeLanguageHindi: Button
    private lateinit var btnChangeLanguageFrench: Button

    private var isLanguageVisible = false

    companion object {
        var selectedLanguage: String = "en"
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(applyLanguage(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing Views
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
       btnChangeLanguage=findViewById(R.id.btnChangeLanguage)
        btnChangeLanguageEnglish = findViewById(R.id.btnChangeLanguageEnglish)
        btnChangeLanguageHindi = findViewById(R.id.btnChangeLanguageHindi)
        btnChangeLanguageFrench = findViewById(R.id.btnChangeLanguageFrench)

        // Initially hide language options
        btnChangeLanguageEnglish.visibility = View.GONE
        btnChangeLanguageHindi.visibility = View.GONE
        btnChangeLanguageFrench.visibility = View.GONE

        // Login Button
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (username == "admin" && password == "admin") {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Toggle visibility for language buttons
        btnChangeLanguage.setOnClickListener {
            isLanguageVisible = !isLanguageVisible
            val visibility = if (isLanguageVisible) View.VISIBLE else View.GONE
            btnChangeLanguageEnglish.visibility = visibility
            btnChangeLanguageHindi.visibility = visibility
            btnChangeLanguageFrench.visibility = visibility
        }

        // Handle language change
        btnChangeLanguageEnglish.setOnClickListener { changeLang("en") }
        btnChangeLanguageHindi.setOnClickListener { changeLang("hi") }
        btnChangeLanguageFrench.setOnClickListener { changeLang("fr") }
    }

    private fun changeLang(language: String) {
        selectedLanguage = language

        // Hide buttons again
        isLanguageVisible = false
        btnChangeLanguageEnglish.visibility = View.GONE
        btnChangeLanguageHindi.visibility = View.GONE
        btnChangeLanguageFrench.visibility = View.GONE

        // Restart activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun applyLanguage(context: Context):Context{
        val locale=Locale(selectedLanguage)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        return  context.createConfigurationContext(config)
    }
}
