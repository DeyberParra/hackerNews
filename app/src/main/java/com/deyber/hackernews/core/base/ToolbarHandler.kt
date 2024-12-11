package com.deyber.hackernews.core.base

interface ToolbarHandler {
    fun showToolbar(show: Boolean = false)
    fun setToolbarTitle(title: String)
    fun handleToolbarBackAction()
}