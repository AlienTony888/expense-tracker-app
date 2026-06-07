package com.alienton.expensetrackerapp.data.repository

import com.alienton.expensetrackerapp.data.local.dao.BudgetDao
import com.alienton.expensetrackerapp.data.local.entity.BudgetEntity
import com.alienton.expensetrackerapp.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BudgetRepository @Inject constructor(
    private val budgetDao: BudgetDao
) {
    suspend fun addBudget(budget: BudgetEntity): Result<Unit> = try {
        budgetDao.insertBudget(budget)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error("Failed to add budget: ${e.message}")
    }

    suspend fun updateBudget(budget: BudgetEntity): Result<Unit> = try {
        budgetDao.updateBudget(budget)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error("Failed to update budget: ${e.message}")
    }

    suspend fun deleteBudget(budget: BudgetEntity): Result<Unit> = try {
        budgetDao.deleteBudget(budget)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error("Failed to delete budget: ${e.message}")
    }

    fun getActiveBudgets(): Flow<Result<List<BudgetEntity>>> = budgetDao.getActiveBudgets()
        .map { Result.Success(it) }
        .catch { emit(Result.Error("Failed to fetch budgets: ${it.message}")) }

    fun getBudgetByCategory(categoryId: String): Flow<Result<BudgetEntity?>> =
        budgetDao.getBudgetByCategory(categoryId)
            .map { Result.Success(it) }
            .catch { emit(Result.Error("Failed to fetch budget: ${it.message}")) }
}