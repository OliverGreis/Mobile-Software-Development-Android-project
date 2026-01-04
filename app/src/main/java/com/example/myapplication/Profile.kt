package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.compose.ui.platform.ComposeView
import com.example.myapplication.screens.SmallTopAppBarExampleXML
import android.app.Activity
import androidx.navigation.NavHostController

class Profile(navController: NavHostController) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val composeHeader = findViewById<ComposeView>(R.id.compose_header)
        composeHeader.setContent {
            SmallTopAppBarExampleXML(
            onBack = {
                println("works")
                setResult(Activity.RESULT_OK)
                finish()
            }
            )
        }

        }
    }
