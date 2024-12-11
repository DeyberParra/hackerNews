package com.deyber.hackernews.ui.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.deyber.hackernews.R
import com.deyber.hackernews.core.base.BaseFragment
import com.deyber.hackernews.databinding.FragmemtHomeNewsBinding
import com.deyber.hackernews.domain.model.ui.NewsResponseModel
import com.deyber.hackernews.ui.adapter.HitsAdapter
import com.deyber.hackernews.ui.fragments.HintDetailFragment.Companion.URL_KEY
import com.deyber.hackernews.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeNewsFragment : BaseFragment<FragmemtHomeNewsBinding>(FragmemtHomeNewsBinding::inflate) {

    private val viewModel: NewsViewModel by viewModels()

    private lateinit var hintAdapter: HitsAdapter

    override fun onResume() {
        super.onResume()
        viewModel.getNews()
    }

    override fun setupUI() {
        super.setupUI()
        showToolbar(false)
        setupAdapter()

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

    override fun setupObserver(){
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
