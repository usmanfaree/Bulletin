package com.example.bulletin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bulletin.R
import com.example.bulletin.adapter.NewsAdapter
import com.example.bulletin.databinding.FragmentNewsBinding
import com.example.bulletin.utils.UiState
import com.example.bulletin.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        //setupViewModel()
        initRecyclerView()
        observeData()

        viewModel.getNews(
            CATEGORY_GENERAL,
            "4adafc67d4497fa34fe411f31e57fd53"
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
        viewModel.newsData.observe(viewLifecycleOwner) { state ->

            when (state) {

                is UiState.Loading -> {
                    Log.d("FRAGMENT_LOG", "Loading...")
                }

                is UiState.Success -> {
                    val articles = state.data.articles
                    newsAdapter.submitList(articles)


                }

                is UiState.CachedData -> {
                    newsAdapter.submitList(state.articles)


                }

                is UiState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        state.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}