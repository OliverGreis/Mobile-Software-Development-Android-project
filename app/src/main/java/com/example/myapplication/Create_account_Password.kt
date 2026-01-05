package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Create_account_Password : AppCompatActivity() {
    lateinit var password: EditText
    lateinit var conformPassword: EditText
    lateinit var nextButton: Button
    lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account_password)

        nextButton.setOnClickListener{
        }

        backButton.setOnClickListener {
            finish()
        }
    }
}