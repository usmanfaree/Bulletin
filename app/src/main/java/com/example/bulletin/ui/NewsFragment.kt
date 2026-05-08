package com.example.bulletin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bulletin.R
import com.example.bulletin.adapter.NewsAdapter
import com.example.bulletin.api.RetrofitInstance
import com.example.bulletin.databinding.FragmentNewsBinding
import com.example.bulletin.db.ArticleDao
import com.example.bulletin.db.NewsDatabase
import com.example.bulletin.repository.NewsRepository
import com.example.bulletin.utils.UiState
import com.example.bulletin.viewmodels.NewsViewModel
import com.example.bulletin.viewmodels.NewsViewModelFactory

class NewsFragment : Fragment(R.layout.fragment_news) {

    // 1. ViewBinding handle karne ka professional tareeqa
    private var _binding: FragmentNewsBinding? = null
    private lateinit var newsAdapter: NewsAdapter
    private val binding get() = _binding!!

    private lateinit var viewModel: NewsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. Binding initialize karna
        _binding = FragmentNewsBinding.bind(view)
        Toast.makeText(context, "Fragment Load Ho Gaya!", Toast.LENGTH_SHORT).show()
        setupViewModel()
        setupRecyclerView() // Pehle RV setup
        observeData()
        //observeSavedArticles()
        viewModel.getNews("general","4adafc67d4497fa34fe411f31e57fd53")


    }

    private fun setupViewModel() {
        val dao= NewsDatabase.getDatabase(requireContext()).articleDao()
        val repository = NewsRepository(RetrofitInstance.apiService,  dao )
        val factory = NewsViewModelFactory(repository)

        viewModel = ViewModelProvider(requireActivity(), factory)[NewsViewModel::class.java]

    }

    private fun observeData() {
        Toast.makeText(requireContext(), "function calling", android.widget.Toast.LENGTH_SHORT).show()

            viewModel.newsData.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is UiState.Loading -> {
                        Log.d("FRAGMENT_LOG", "Data loading start ho gaya...")
                        Toast.makeText(context, "data loading hai jee", Toast.LENGTH_SHORT).show()
                    }
                    is UiState.Success -> {
                        val articles = state.data.articles
                        newsAdapter.submitList(articles)
                        Toast.makeText(requireContext(), "\"Mubarak ho! ${articles.size} news mil gayin!", android.widget.Toast.LENGTH_SHORT).show()


                        // Pehli news ka title print karo verify karne ke liye
                        if (articles.isNotEmpty()) {
                            Log.d("FRAGMENT_LOG", "Title: ${articles[0].title}")
                        }
                    }
                    is UiState.Error -> {

                        Toast.makeText(requireContext(), "Data khali aaya hai!", android.widget.Toast.LENGTH_SHORT).show()
                    }
                }
            }

    }

    private fun setupRecyclerView()
    {
        newsAdapter = NewsAdapter()
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }



    }

    private fun observeSavedArticles() {
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            newsAdapter.submitList(articles)
        }
    }



    // 3. Sab se important point: Memory Leak se bachna
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}