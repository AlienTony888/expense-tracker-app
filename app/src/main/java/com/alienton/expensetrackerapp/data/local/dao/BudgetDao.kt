package com.alienton.expensetrackerapp.data.local.dao

import androidx.room.*
import com.alienton.expensetrackerapp.data.local.entity.BudgetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBudget(budget: BudgetEntity)

    @Update
    suspend fun updateBudget(budget: BudgetEntity)

    @Delete
    suspend fun deleteBudget(budget: BudgetEntity)

    @Query("SELECT * FROM budgets WHERE id = :id")
    suspend fun getBudgetById(id: String): BudgetEntity?

    @Query("SELECT * FROM budgets WHERE isActive = 1 ORDER BY endDate ASC")
    fun getActiveBudgets(): Flow<List<BudgetEntity>>

    @Query("SELECT * FROM budgets WHERE categoryId = :categoryId AND isActive = 1")
    fun getBudgetByCategory(categoryId: String): Flow<BudgetEntity?>

    @Query("UPDATE budgets SET spent = :spent WHERE id = :id")
    suspend fun updateBudgetSpent(id: String, spent: Double)
}