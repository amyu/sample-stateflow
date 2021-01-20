package com.example.sample2021.ui.sonota

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sample2021.databinding.ItemLoadingBinding


class LoadingViewHolder
private constructor(
    private val binding: ItemLoadingBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): LoadingViewHolder =
            LoadingViewHolder(
                binding = ItemLoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    object Item : ViewHolderItem() {
        override val id: Id = Id("loading")
        override fun equals(other: Any?): Boolean = other is Item

        override fun hashCode(): Int = 0
    }
}