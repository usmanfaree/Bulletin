package com.example.bulletin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bulletin.R
import com.example.bulletin.adapter.NewsAdapter
import com.example.bulletin.databinding.FragmentNewsBinding
import com.example.bulletin.utils.Resource
import com.example.bulletin.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val CATEGORY_GENERAL = "general"
        const val KEY_TITLE = "title"
        const val KEY_DESCRIPTION = "description"
        const val KEY_IMAGE = "image"
        const val KEY_URL = "url"
    }

    private lateinit var newsAdapter: NewsAdapter
    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentNewsBinding.bind(view)

        initRecyclerView()
        observeData()

        viewModel.getNews(
            CATEGORY_GENERAL,
            "ef34ec679388b4c1f2732acec04c3a2c"
        )
    }

    private fun initRecyclerView() {
        newsAdapter = NewsAdapter { article ->
            val bundle = Bundle().apply {
                putString(KEY_TITLE, article.title)
                putString(KEY_DESCRIPTION, article.description)
                putString(KEY_IMAGE, article.urlToImage)
                putString(KEY_URL, article.url)
            }

            findNavController().navigate(
                R.id.action_homeFragment_to_newsDetail,
                bundle
            )
        }

        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsState.collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            Log.d("FRAGMENT_LOG", "Loading...")
                        }

                        is Resource.Success -> {
                            val articles = resource.data ?: emptyList()
                            newsAdapter.submitList(articles)
                        }

                        is Resource.Error -> {
                            resource.data?.let { cachedArticles ->
                                newsAdapter.submitList(cachedArticles)
                            }

                            Toast.makeText(
                                requireContext(),
                                resource.message ?: "An unexpected error occurred",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}