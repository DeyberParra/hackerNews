package com.deyber.hackernews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.deyber.hackernews.databinding.FragmemtHomeNewsBinding

class HomeNewsFragment : Fragment() {

    private lateinit var binding : FragmemtHomeNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmemtHomeNewsBinding.inflate(layoutInflater)
        return binding.root
    }
}
