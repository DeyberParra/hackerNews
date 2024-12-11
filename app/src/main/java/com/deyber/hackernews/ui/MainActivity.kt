package com.deyber.hackernews.ui

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.deyber.hackernews.R
import com.deyber.hackernews.core.base.ToolbarHandler
import com.deyber.hackernews.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ToolbarHandler {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        setupBackPressed()
        supportActionBar?.hide()
        binding.toolbarIcon.setOnClickListener { handleToolbarBackAction() }

    }

    private fun setupBackPressed() {
        onBackPressedDispatcher.addCallback(this) {
            if (!navController.navigateUp()) {
                finish()
            }
        }
    }

    override fun showToolbar(show: Boolean) {
        binding.toolbar.isVisible = show
    }

    override fun setToolbarTitle(title: String) {
        binding.toolBarTitle.text = title
    }

    override fun handleToolbarBackAction() {
        onBackPressedDispatcher.onBackPressed()
    }


}
