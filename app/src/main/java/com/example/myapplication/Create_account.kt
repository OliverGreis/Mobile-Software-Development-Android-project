package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Create_account : AppCompatActivity() {

    lateinit var firstname: EditText
    lateinit var middlename: EditText
    lateinit var lastname: EditText
    lateinit var email: EditText
    lateinit var phonenumber: EditText
    lateinit var profilepicture: ImageView
    lateinit var nextButton: Button
    lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        firstname = findViewById(R.id.First_name_input)
        middlename = findViewById(R.id.Middle_name_input)
        lastname = findViewById(R.id.Last_name_input)
        email = findViewById(R.id.Email_input)
        phonenumber = findViewById(R.id.Phone_number_input)
        profilepicture = findViewById(R.id.Profile_picture_input)
        nextButton = findViewById(R.id.Next_button)
        backButton = findViewById(R.id.back_button)

        backButton.setOnClickListener{
            finish()
        }

        nextButton.setOnClickListener{
        }
    }
}


