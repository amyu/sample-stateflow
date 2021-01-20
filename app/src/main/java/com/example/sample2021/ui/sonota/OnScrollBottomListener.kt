package com.example.sample2021.ui.sonota

import androidx.recyclerview.widget.RecyclerView


class OnScrollBottomListener(
    val onScrolledBottom: () -> Unit
) : RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        if (!recyclerView.canScrollVertically(1)) {
            onScrolledBottom()
        }
    }
}