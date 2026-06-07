package com.alienton.expensetrackerapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alienton.expensetrackerapp.domain.model.Result
import com.alienton.expensetrackerapp.domain.usecase.GetExpensesUseCase
import com.alienton.expensetrackerapp.domain.usecase.GetCategoriesUseCase
import com.alienton.expensetrackerapp.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class HomeViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getExpensesUseCase: GetExpensesUseCase

    @Mock
    private lateinit var getCategoriesUseCase: GetCategoriesUseCase

    @Test
    fun testLoadExpensesSuccess() = runTest {
        MockitoAnnotations.openMocks(this@HomeViewModelTest)
        
        val mockExpenses = emptyList<com.alienton.expensetrackerapp.data.local.entity.ExpenseEntity>()
        whenever(getExpensesUseCase.invoke()).thenReturn(
            flowOf(Result.Success(mockExpenses))
        )
        whenever(getCategoriesUseCase.invoke()).thenReturn(
            flowOf(Result.Success(emptyList()))
        )

        val viewModel = HomeViewModel(getExpensesUseCase, getCategoriesUseCase)
    }
}