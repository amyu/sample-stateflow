package com.example.sample2021.ui.screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sample2021.domain.GetSampleListUseCase
import com.example.sample2021.domain.SampleId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class SampleViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getSampleListUseCase: GetSampleListUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onClickCheckAll, itemsはすべてCheckされている状態になっている`() = runBlockingTest {
        Mockito.`when`(getSampleListUseCase.execute()).thenReturn(emptyList())

        val viewModel = SampleViewModel(getSampleListUseCase)
        viewModel.uiState.value = viewModel.uiState.value.copy(
            items = listOf(
                SampleViewHolder.Item(
                    id = SampleId(UUID.randomUUID().toString()),
                    name = UUID.randomUUID().toString(),
                    isChecked = false
                ),
                SampleViewHolder.Item(
                    id = SampleId(UUID.randomUUID().toString()),
                    name = UUID.randomUUID().toString(),
                    isChecked = false
                ),
                SampleViewHolder.Item(
                    id = SampleId(UUID.randomUUID().toString()),
                    name = UUID.randomUUID().toString(),
                    isChecked = false
                )
            )
        )

        viewModel.onClickCheckAll()

        Assert.assertTrue(
            viewModel.uiState.value.items.filterIsInstance<SampleViewHolder.Item>()
                .all { it.isChecked }
        )
    }
}