package com.deyber.hackernews.ui.fragments

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.deyber.hackernews.core.base.BaseFragment
import com.deyber.hackernews.databinding.FragmentHintDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HintDetailFragment :
    BaseFragment<FragmentHintDetailBinding>(FragmentHintDetailBinding::inflate) {

    companion object {
        const val URL_KEY = "#URL"
    }

    private val url: String? by lazy {
        requireArguments().getString(URL_KEY)
    }

    override fun setupUI() {
        super.setupUI()
        showToolbar(true)
        setupToolbarTitle("Back")
        url?.let {
            setupWebView(it)
        }

    }

    private fun setupWebView(url: String) {
        with(binding.webView) {
            binding.progressBar.visibility = View.VISIBLE

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.progressBar.visibility = View.GONE
                }

                override fun onPageStarted(
                    view: WebView?,
                    url: String?,
                    favicon: android.graphics.Bitmap?
                ) {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }

            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
            }
            loadUrl(url)
        }
    }

    override fun onDestroyView() {
        binding.webView.apply {
            clearHistory()
            clearCache(true)
            loadUrl("about:blank")
            onPause()
            removeAllViews()
            destroy()
        }
        super.onDestroyView()
    }
}
