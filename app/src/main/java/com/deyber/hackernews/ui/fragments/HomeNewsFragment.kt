package com.deyber.hackernews.ui.fragments

import SwipeToDeleteCallback
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.deyber.hackernews.R
import com.deyber.hackernews.core.base.BaseFragment
import com.deyber.hackernews.core.network.Resource
import com.deyber.hackernews.core.network.doFailure
import com.deyber.hackernews.core.network.doLoading
import com.deyber.hackernews.core.network.doSuccess
import com.deyber.hackernews.databinding.FragmemtHomeNewsBinding
import com.deyber.hackernews.domain.model.ui.HitModel
import com.deyber.hackernews.ui.adapter.HitsAdapter
import com.deyber.hackernews.ui.fragments.HintDetailFragment.Companion.URL_KEY
import com.deyber.hackernews.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeNewsFragment : BaseFragment<FragmemtHomeNewsBinding>(FragmemtHomeNewsBinding::inflate) {

    private val viewModel: NewsViewModel by viewModels()

    private lateinit var hintAdapter: HitsAdapter


    override fun setupUI() {
        super.setupUI()
        showToolbar(false)
        setupAdapter()
        viewModel.getNews()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getNews()
            binding.swipeRefreshLayout.isRefreshing = false
        }

    }

    private fun setupAdapter() {
        hintAdapter = HitsAdapter { hintSelected ->
            if (!hintSelected.storyUrl.isNullOrEmpty()) {
                val bundle = Bundle().apply {
                    putString(URL_KEY, hintSelected.storyUrl)
                }
                findNavController().navigate(R.id.action_to_detail_hint, bundle)
            } else {
                Toast.makeText(requireContext(), "Sin  contenido que mostrar", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.hintRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = hintAdapter
            val dividerItemDecoration = DividerItemDecoration(
                context,
                (layoutManager as LinearLayoutManager).orientation
            )

            addItemDecoration(dividerItemDecoration)

            val itemTouchHelper =
                ItemTouchHelper(SwipeToDeleteCallback(requireContext()) { position ->
                    val item = hintAdapter.getItemByPosition(position)
                    hintAdapter.removeItem(position)
                    item?.let {
                        viewModel.deleteHit(it)
                    }
                })
            itemTouchHelper.attachToRecyclerView(binding.hintRv)
        }
    }

    override fun setupObserver() {
        with(viewModel) {
            hits.observe(viewLifecycleOwner, ::processHitsList)
        }
    }

    private fun processHitsList(resource: Resource<List<HitModel>>) {
        with(binding) {
            resource.doSuccess { list ->
                hintAdapter.setupHint(list)
                progressBar.isVisible = false
            }
            resource.doLoading {
                progressBar.isVisible = true
            }
            resource.doFailure { error, throwable, typeErrorType ->
                progressBar.isVisible = false
            }
        }
    }
}
