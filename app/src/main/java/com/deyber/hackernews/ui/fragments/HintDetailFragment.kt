package com.deyber.hackernews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.deyber.hackernews.databinding.FragmentHintDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HintDetailFragment :Fragment() {

    companion object {
        const val URL_KEY = "#URL"
    }

    private lateinit var binding: FragmentHintDetailBinding

    private val url: String? by lazy {
        requireArguments().getString(URL_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentHintDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        url?.let {
            setupWebView(it)
        }

    }

    private fun setupWebView(url: String){
        with(binding.webView) {
            // Mostrar el ProgressBar cuando empiece a cargar
            binding.progressBar.visibility = View.VISIBLE

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    // Ocultar el ProgressBar cuando la página haya terminado de cargar
                    binding.progressBar.visibility = View.GONE
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                    // Mostrar el ProgressBar cuando la página empiece a cargar
                    binding.progressBar.visibility = View.VISIBLE
                }
            }

            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
            }

            loadUrl(url) // Cargar URL
    }
    }
}