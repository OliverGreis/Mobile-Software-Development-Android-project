package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers


class Authentication : AppCompatActivity()
{
    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginButton: Button
    lateinit var createAccount: Button
    lateinit var forgotPassword: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        createAccount = findViewById(R.id.create_account_button)
        forgotPassword = findViewById(R.id.forgot_password)


        loginButton.setOnClickListener{
            handleLogin(usernameInput.text.toString(), passwordInput.text.toString())
        }

        forgotPassword.setOnClickListener{
            startActivity(Intent(this, Forgot_password::class.java))
        }
    }

    private fun handleLogin(username: String, password: String)
    {
        if (username.isEmpty()||password.isEmpty())
        {
            Toast.makeText(this,"please file out username and password", Toast.LENGTH_SHORT).show()
        }
    }


}







