package com.example.bulletin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bulletin.R
import com.example.bulletin.databinding.FragmentNewsDetailBinding

class NewsDetail : Fragment(R.layout.fragment_news_detail) {

    private var _binding: FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentNewsDetailBinding.bind(view)

        val title = arguments?.getString("title")
        val description = arguments?.getString("description")
        val image = arguments?.getString("image")
        Log.d("IMG_TEST", "Image URL: $image")
        val url = arguments?.getString("url")

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