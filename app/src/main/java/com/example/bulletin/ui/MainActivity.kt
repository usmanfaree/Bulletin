package com.example.bulletin.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.bulletin.R
import com.example.bulletin.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.topAppBar.inflateMenu(R.menu.top_menu)
        WindowInsetsControllerCompat(window, window.decorView)
            .isAppearanceLightStatusBars = false

        ViewCompat.setOnApplyWindowInsetsListener(binding.appBarContainer) { view, insets ->

            val statusBarHeight =
                insets.getInsets(WindowInsetsCompat.Type.statusBars()).top

            view.setPadding(
                0,
                statusBarHeight,
                0,
                0
            )

            insets
        }

        setupToolbar()
    }

    private fun setupToolbar() {

        binding.topAppBar.setOnMenuItemClickListener { item ->

            when (item.itemId) {

                R.id.action_search -> {
                    Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
                    true
                }



                else -> false
            }
        }
    }
}