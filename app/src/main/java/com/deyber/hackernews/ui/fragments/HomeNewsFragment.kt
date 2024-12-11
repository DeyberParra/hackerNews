package com.deyber.hackernews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.deyber.hackernews.R
import com.deyber.hackernews.databinding.FragmemtHomeNewsBinding
import com.deyber.hackernews.domain.model.ui.NewsResponseModel
import com.deyber.hackernews.ui.adapter.HitsAdapter
import com.deyber.hackernews.ui.fragments.HintDetailFragment.Companion.URL_KEY
import com.deyber.hackernews.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeNewsFragment : Fragment() {

    private lateinit var binding : FragmemtHomeNewsBinding
    private val viewModel: NewsViewModel by viewModels()

    private lateinit var hintAdapter: HitsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmemtHomeNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNews()
    }

    private fun setupAdapter(){
        hintAdapter = HitsAdapter { hintSelected ->
            hintSelected.storyUrl?.let {
                val bundle = Bundle().apply {
                    putString(URL_KEY, it)
                }
                findNavController().navigate(R.id.action_to_detail_hint, bundle)
            } ?: run {
                Toast.makeText(requireContext() ,"Sin  contenido que mostrar", Toast.LENGTH_LONG).show()
            }

        }

        binding.hintRv.apply {
            layoutManager= LinearLayoutManager(requireContext())
            adapter = hintAdapter
            val dividerItemDecoration = DividerItemDecoration(
                context,
                (layoutManager as LinearLayoutManager).orientation
            )
            addItemDecoration(dividerItemDecoration)
        }
    }

    private fun setupObserver(){
        with(viewModel){
            news.observe(viewLifecycleOwner, ::processHitsList)
        }
    }

    private fun processHitsList(newsResponseModel: NewsResponseModel?) {
        newsResponseModel?.let {
            hintAdapter.setupHint(it.hits.orEmpty())
        }
    }
}
