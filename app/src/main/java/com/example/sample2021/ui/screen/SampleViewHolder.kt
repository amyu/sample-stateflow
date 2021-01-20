package com.example.sample2021.ui.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sample2021.domain.Identifier
import com.example.sample2021.databinding.ItemSampleBinding
import com.example.sample2021.domain.Sample
import com.example.sample2021.ui.sonota.ViewHolderItem


class SampleViewHolder
private constructor(
    private val binding: ItemSampleBinding
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(parent: ViewGroup): SampleViewHolder =
            SampleViewHolder(
                binding = ItemSampleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

    fun bind(
        item: Item,
        onClickCheck: (Item) -> Unit
    ) {
        binding.item = item
        binding.checkBox.setOnClickListener {
            onClickCheck(item)
        }
    }

    data class Item(
        override val id: Identifier<String>,
        val name: String,
        //これは初期値が決まっているからDefault引数
        val isChecked: Boolean = false
    ) : ViewHolderItem() {
        companion object {
            fun convertItem(sample: Sample): SampleViewHolder.Item = SampleViewHolder.Item(
                id = sample.id,
                name = "${sample.firstName}  ${sample.lastName}"
            )
        }
    }
}