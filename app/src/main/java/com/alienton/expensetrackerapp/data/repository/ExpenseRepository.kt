package com.alienton.expensetrackerapp.data.repository

import com.alienton.expensetrackerapp.data.local.dao.ExpenseDao
import com.alienton.expensetrackerapp.data.local.entity.ExpenseEntity
import com.alienton.expensetrackerapp.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseRepository @Inject constructor(
    private val expenseDao: ExpenseDao
) {
    suspend fun addExpense(expense: ExpenseEntity): Result<Unit> = try {
        expenseDao.insertExpense(expense)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error("Failed to add expense: ${e.message}")
    }

    suspend fun updateExpense(expense: ExpenseEntity): Result<Unit> = try {
        expenseDao.updateExpense(expense)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error("Failed to update expense: ${e.message}")
    }

    suspend fun deleteExpense(id: String): Result<Unit> = try {
        expenseDao.softDeleteExpense(id)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error("Failed to delete expense: ${e.message}")
    }

    fun getAllExpenses(): Flow<Result<List<ExpenseEntity>>> = expenseDao.getAllExpenses()
        .map { Result.Success(it) }
        .catch { emit(Result.Error("Failed to fetch expenses: ${it.message}")) }

    fun getExpensesByCategory(categoryId: String): Flow<Result<List<ExpenseEntity>>> =
        expenseDao.getExpensesByCategory(categoryId)
            .map { Result.Success(it) }
            .catch { emit(Result.Error("Failed to fetch expenses by category: ${it.message}")) }

    fun getExpensesByDateRange(startDate: Date, endDate: Date): Flow<Result<List<ExpenseEntity>>> =
        expenseDao.getExpensesByDateRange(startDate, endDate)
            .map { Result.Success(it) }
            .catch { emit(Result.Error("Failed to fetch expenses by date range: ${it.message}")) }

    fun getTotalExpenses(): Flow<Result<Double>> = expenseDao.getTotalExpenses()
        .map { Result.Success(it ?: 0.0) }
        .catch { emit(Result.Error("Failed to fetch total: ${it.message}")) }

    fun getTotalExpensesByDateRange(startDate: Date, endDate: Date): Flow<Result<Double>> =
        expenseDao.getTotalExpensesByDateRange(startDate, endDate)
            .map { Result.Success(it ?: 0.0) }
            .catch { emit(Result.Error("Failed to fetch total by date range: ${it.message}")) }
}