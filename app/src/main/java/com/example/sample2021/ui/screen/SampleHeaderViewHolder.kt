package com.example.sample2021.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sample2021.domain.Identifier
import com.example.sample2021.databinding.ItemSampleHeaderBinding
import com.example.sample2021.ui.sonota.ViewHolderItem


class SampleHeaderViewHolder
private constructor(
    private val binding: ItemSampleHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): SampleHeaderViewHolder =
            SampleHeaderViewHolder(
                binding = ItemSampleHeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    fun bind(item: Item) {
        binding.item = item
    }

    object Item : ViewHolderItem() {
        override val id: Identifier<String> = Id("SampleHeaderViewHolder")

        override fun equals(other: Any?): Boolean = other is Item

        override fun hashCode(): Int = 0
    }
}