package com.example.sample2021.ui.screen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sample2021.R
import com.example.sample2021.databinding.FragmentSampleBinding
import com.example.sample2021.ui.dialog.SampleDialog
import com.example.sample2021.ui.sonota.OnScrollBottomListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SampleFragment : Fragment(R.layout.fragment_sample) {
    companion object {
        fun newInstance(): SampleFragment = SampleFragment()
    }

    private val viewModel: SampleViewModel by viewModels()

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSampleBinding.bind(view)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.checkAllButton.setOnClickListener { viewModel.onClickCheckAll() }
        binding.recyclerView.adapter = SampleAdapter(
            onClickCheck = viewModel::onClickCheck
        )
        binding.recyclerView.addOnScrollListener(OnScrollBottomListener { viewModel.onScrolledBottom() })
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int =
                    if (binding.recyclerView.adapter?.getItemViewType(position) == SampleAdapter.CONTENT) {
                        1
                    } else {
                        2
                    }
            }
        }
        binding.showSampleDialogButton.setOnClickListener {
            viewModel.onClickShowSampleDialog()
        }
        binding.showInputText.setOnClickListener {
            //viewModel.uiState.value この書き方は本当はだめ
            Toast.makeText(requireContext(), viewModel.uiState.value.inputText, Toast.LENGTH_SHORT)
                .show()
        }
        binding.moveButton.setOnClickListener {
            viewModel.onClickMove()
        }

        viewModel.uiState.asLiveData().observe(viewLifecycleOwner) {

        }
        lifecycleScope.launchWhenStarted {
            //collect はsuspend function
            viewModel.uiState.collect { uiState ->
                (binding.recyclerView.adapter as SampleAdapter).submitList(uiState.items)

                if (uiState.isVisibleDialog) {
                    SampleDialog.show(
                        target = this@SampleFragment,
                        onDismiss = viewModel::onDismissSampleDialog
                    )
                } else {
                    // ほんとはここに強制的にDismissさせるような処理をかけ寺いいな
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.moveToHoge.collect {
                Toast.makeText(requireContext(), "画面遷移", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}