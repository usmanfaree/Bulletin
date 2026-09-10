package com.example.bulletin.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bulletin.R
import com.example.bulletin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
    }

    private fun setupToolbar() {

        binding.topAppBar.setOnMenuItemClickListener { item ->

            when (item.itemId) {

                R.id.action_search -> {
                    Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.action_refresh -> {
                    Toast.makeText(this, "Refresh clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }
}