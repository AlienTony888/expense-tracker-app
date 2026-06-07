package com.alienton.expensetrackerapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alienton.expensetrackerapp.domain.usecase.GetExpensesUseCase
import com.alienton.expensetrackerapp.domain.usecase.GetCategoriesUseCase
import com.alienton.expensetrackerapp.data.local.entity.ExpenseEntity
import com.alienton.expensetrackerapp.data.local.entity.CategoryEntity
import com.alienton.expensetrackerapp.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {
    
    private val _expenses = MutableStateFlow<Result<List<ExpenseEntity>>>(Result.Loading)
    val expenses: StateFlow<Result<List<ExpenseEntity>>> = _expenses.asStateFlow()

    private val _categories = MutableStateFlow<Result<List<CategoryEntity>>>(Result.Loading)
    val categories: StateFlow<Result<List<CategoryEntity>>> = _categories.asStateFlow()

    private val _totalExpenses = MutableStateFlow(0.0)
    val totalExpenses: StateFlow<Double> = _totalExpenses.asStateFlow()

    init {
        loadExpenses()
        loadCategories()
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            getExpensesUseCase().collect { result ->
                _expenses.value = result
                if (result is Result.Success) {
                    _totalExpenses.value = result.data.sumOf { it.amount }
                }
            }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { result ->
                _categories.value = result
            }
        }
    }

    fun refreshData() {
        loadExpenses()
        loadCategories()
    }
}