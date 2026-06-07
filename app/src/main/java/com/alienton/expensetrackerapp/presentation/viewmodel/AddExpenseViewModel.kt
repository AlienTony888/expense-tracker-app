package com.alienton.expensetrackerapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alienton.expensetrackerapp.domain.usecase.AddExpenseUseCase
import com.alienton.expensetrackerapp.domain.usecase.GetCategoriesUseCase
import com.alienton.expensetrackerapp.data.local.entity.ExpenseEntity
import com.alienton.expensetrackerapp.data.local.entity.CategoryEntity
import com.alienton.expensetrackerapp.domain.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val addExpenseUseCase: AddExpenseUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _categories = MutableStateFlow<Result<List<CategoryEntity>>>(Result.Loading)
    val categories: StateFlow<Result<List<CategoryEntity>>> = _categories.asStateFlow()

    private val _addResult = MutableStateFlow<Result<Unit>>(Result.Success(Unit))
    val addResult: StateFlow<Result<Unit>> = _addResult.asStateFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { result ->
                _categories.value = result
            }
        }
    }

    fun addExpense(
        amount: Double,
        description: String,
        categoryId: String,
        tags: List<String> = emptyList(),
        paymentMethod: String = "CASH",
        date: Date = Date()
    ) {
        viewModelScope.launch {
            val expense = ExpenseEntity(
                amount = amount,
                description = description,
                categoryId = categoryId,
                tags = tags.joinToString(","),
                paymentMethod = paymentMethod,
                date = date
            )
            val result = addExpenseUseCase(expense)
            _addResult.value = result
        }
    }
}