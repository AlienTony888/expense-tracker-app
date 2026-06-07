package com.alienton.expensetrackerapp.domain.usecase

import com.alienton.expensetrackerapp.data.repository.ExpenseRepository
import com.alienton.expensetrackerapp.data.local.entity.ExpenseEntity
import com.alienton.expensetrackerapp.domain.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetExpensesUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository
) {
    operator fun invoke(): Flow<Result<List<ExpenseEntity>>> = expenseRepository.getAllExpenses()
}