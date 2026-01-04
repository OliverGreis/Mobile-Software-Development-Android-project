package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class Forgot_password : AppCompatActivity()
{
    lateinit var email: EditText
    lateinit var resetButton: Button
    lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        email = findViewById(R.id.Email_input)
        resetButton = findViewById(R.id.Reset_password)
        backButton = findViewById(R.id.back_button)

        resetButton.setOnClickListener { finish() }

        backButton.setOnClickListener { finish() }
    }
}