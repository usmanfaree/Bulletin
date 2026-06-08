package com.example.bulletin.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bulletin.R
import com.example.bulletin.databinding.FragmentNewsDetailBinding

class NewsDetail : Fragment(R.layout.fragment_news_detail) {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val KEY_TITLE = "title"
        const val KEY_DESCRIPTION = "description"
        const val KEY_IMAGE = "image"
        const val KEY_URL = "url"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentNewsDetailBinding.bind(view)

        val title = arguments?.getString(KEY_TITLE) ?: "No Title"
        val description = arguments?.getString(KEY_DESCRIPTION) ?: "No Description"
        val image = arguments?.getString(KEY_IMAGE)
        val url = arguments?.getString(KEY_URL) ?: ""

        bindData(title, description, image, url)
    }

    private fun bindData(
        title: String,
        description: String,
        image: String?,
        url: String
    ) {
        binding.tvDetailTitle.text = title
        binding.tvDetailDescription.text = description
        binding.tvDetailUrl.text = url

        Glide.with(requireContext())
            .load(image)
            .placeholder(android.R.drawable.progress_horizontal)
            .error(R.drawable.newspaper)
            .into(binding.ivDetailImage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}