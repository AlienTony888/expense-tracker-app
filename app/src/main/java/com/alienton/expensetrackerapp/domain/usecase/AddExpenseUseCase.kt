package com.alienton.expensetrackerapp.domain.usecase

import com.alienton.expensetrackerapp.data.repository.ExpenseRepository
import com.alienton.expensetrackerapp.data.local.entity.ExpenseEntity
import com.alienton.expensetrackerapp.domain.model.Result
import javax.inject.Inject

class AddExpenseUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository
) {
    suspend operator fun invoke(expense: ExpenseEntity): Result<Unit> {
        if (expense.amount <= 0) {
            return Result.Error("Amount must be greater than 0")
        }
        if (expense.description.isBlank()) {
            return Result.Error("Description cannot be empty")
        }
        return expenseRepository.addExpense(expense)
    }
}