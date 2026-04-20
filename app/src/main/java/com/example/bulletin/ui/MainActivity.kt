package com.example.bulletin.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.bulletin.R
import com.example.bulletin.api.RetrofitInstance
import com.example.bulletin.databinding.ActivityMainBinding
import com.example.bulletin.repository.NewsRepository
import com.example.bulletin.utils.UiState
import com.example.bulletin.viewmodels.NewsViewModel
import com.example.bulletin.viewmodels.NewsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigation graph ko choro, ye line seedha Fragment load karegi
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.newsNavHostFragment, NewsFragment())
                .commit()
        }


    }
}
