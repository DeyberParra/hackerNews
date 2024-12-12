package com.deyber.hackernews.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.deyber.hackernews.ui.MainActivity

abstract class BaseFragment<T : ViewBinding>(private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T) :
    Fragment() {

    private var _binding: T? = null

    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
        setupListener()

    }

    open fun setupUI() {}

    open fun setupObserver() {}

    open fun setupListener() {}

    protected fun showToolbar(isVisible: Boolean = true) {
        if (activity is MainActivity) {
            (activity as MainActivity).showToolbar(isVisible)
        }
    }

    protected fun setupToolbarTitle(title: String) {
        if (activity is MainActivity) {
            (activity as MainActivity).setToolbarTitle(title)
        }
    }


}