package com.example.sample2021.ui.screen

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sample2021.ui.sonota.LoadingViewHolder
import com.example.sample2021.ui.sonota.SimpleListAdapterDiffCallback
import com.example.sample2021.ui.sonota.ViewHolderItem


class SampleAdapter(
    private val onClickCheck: (SampleViewHolder.Item) -> Unit
) : ListAdapter<ViewHolderItem, RecyclerView.ViewHolder>(SimpleListAdapterDiffCallback()) {
    /**
     * ListAdapter<T, VH>のT,VHそれぞれをsealed classで定義すればシンプルになるかもだけど､全然強制はしない
     */

    companion object {
        const val HEADER = 0
        const val CONTENT = 1
        const val LOADING = 2
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is SampleHeaderViewHolder.Item -> HEADER
            is SampleViewHolder.Item -> CONTENT
            is LoadingViewHolder.Item -> LOADING
            else -> throw IllegalAccessError("ここには来ないはず")
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when (viewType) {
            HEADER -> SampleHeaderViewHolder.create(parent)
            CONTENT -> SampleViewHolder.create(parent)
            LOADING -> LoadingViewHolder.create(parent)
            else -> throw IllegalAccessError("ここには来ないはず")
        }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is SampleHeaderViewHolder -> holder.bind(
                item = getItem(position) as SampleHeaderViewHolder.Item
            )
            is SampleViewHolder -> holder.bind(
                item = getItem(position) as SampleViewHolder.Item,
                onClickCheck = onClickCheck
            )
            is LoadingViewHolder -> {
                //nop
            }
        }
    }
}