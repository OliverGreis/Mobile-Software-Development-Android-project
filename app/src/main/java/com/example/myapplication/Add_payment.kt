package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class Add_payment : AppCompatActivity()
{
  lateinit var Card: CardView
  lateinit var account: CardView
  lateinit var createButton: Button
  lateinit var backButton: Button

  // card view
  lateinit var cardNumberInput: EditText
  lateinit var expiryDateInput: EditText
  lateinit var CVCInput: EditText

  // account view
  lateinit var accountNumberInput: EditText
  lateinit var accountNameInput: EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_payment)
    }
}




