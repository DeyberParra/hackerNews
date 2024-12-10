package com.deyber.hackernews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.deyber.hackernews.databinding.FragmemtHomeNewsBinding
import com.deyber.hackernews.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeNewsFragment : Fragment() {

    private lateinit var binding : FragmemtHomeNewsBinding
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmemtHomeNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNews()
    }
}
