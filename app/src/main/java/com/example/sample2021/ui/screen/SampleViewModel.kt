package com.example.sample2021.ui.screen

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample2021.domain.GetSampleListUseCase
import com.example.sample2021.ui.sonota.LoadingViewHolder
import com.example.sample2021.ui.sonota.ViewHolderItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class SampleViewModel
@ViewModelInject constructor(
    private val getSampleListUseCase: GetSampleListUseCase,
) : ViewModel() {
    val uiState: MutableStateFlow<SampleUiState> =
        MutableStateFlow(SampleUiState.initializeState())

    val moveToHoge: MutableSharedFlow<Unit> = MutableSharedFlow()

    init {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                visibilityProgress = View.VISIBLE,
            )

            val samples = getSampleListUseCase.execute()

            uiState.value = uiState.value.copy(
                items = uiState.value.items +
                        samples.map(SampleViewHolder.Item::convertItem) + LoadingViewHolder.Item,
                visibilityProgress = View.GONE,
            )
        }
    }

    fun onClickCheck(item: SampleViewHolder.Item) {
        uiState.value = uiState.value.copy(
            items = uiState.value.items.map {
                if (it == item) {
                    item.copy(isChecked = !item.isChecked)
                } else {
                    it
                }
            }
        )
    }

    fun onClickCheckAll() {
        uiState.value = uiState.value.copy(
            items = uiState.value.items.map {
                when (it) {
                    is SampleViewHolder.Item -> it.copy(isChecked = true)
                    is SampleHeaderViewHolder.Item -> it
                    else -> it
                }
            }
        )
    }

    fun onClickShowSampleDialog() {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                isVisibleDialog = true
            )
        }
    }

    fun onDismissSampleDialog() {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                isVisibleDialog = false
            )
        }
    }

    fun onScrolledBottom() {
        viewModelScope.launch {
            val samples = getSampleListUseCase.execute()

            uiState.value = uiState.value.copy(
                items = uiState.value.items.filterNot { it is LoadingViewHolder.Item } + samples.map(
                    SampleViewHolder.Item::convertItem
                ) + LoadingViewHolder.Item,
            )
        }
    }

    fun onClickMove() {
        viewModelScope.launch {
            moveToHoge.emit(Unit)
        }
    }

    data class SampleUiState(
        val items: List<ViewHolderItem>,
        var inputText: String,
        val visibilityProgress: Int,
        val isVisibleDialog: Boolean,
    ) {
        companion object {
            fun initializeState(): SampleUiState = SampleUiState(
                items = listOf(SampleHeaderViewHolder.Item),
                inputText = "",
                visibilityProgress = View.GONE,
                isVisibleDialog = false
            )
        }

        val isEnableAllCheckButton: Boolean =
            items.filterIsInstance<SampleViewHolder.Item>().any { !it.isChecked }
    }
}