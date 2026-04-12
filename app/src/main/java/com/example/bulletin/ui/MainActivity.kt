package com.example.bulletin.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.bulletin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val testTv = findViewById<TextView>(R.id.testTextView)
        val viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        viewModel.newsData.observe(this) { response ->
            // Bus yeh print karke dekho data aaya ya nahi
            Log.d("CHECK", "Data Response: ${response.body()?.articles?.get(0)?.title}")
            testTv.text = "Success: $title"
        }

        viewModel.fetchNews("general", "TERI_API_KEY")


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}