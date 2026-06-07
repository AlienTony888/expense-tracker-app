package com.alienton.expensetrackerapp.data.local.dao

import androidx.room.*
import com.alienton.expensetrackerapp.data.local.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(expenses: List<ExpenseEntity>)

    @Update
    suspend fun updateExpense(expense: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntity)

    @Query("SELECT * FROM expenses WHERE id = :id")
    suspend fun getExpenseById(id: String): ExpenseEntity?

    @Query("SELECT * FROM expenses WHERE isDeleted = 0 ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expenses WHERE categoryId = :categoryId AND isDeleted = 0 ORDER BY date DESC")
    fun getExpensesByCategory(categoryId: String): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate AND isDeleted = 0 ORDER BY date DESC")
    fun getExpensesByDateRange(startDate: Date, endDate: Date): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate AND categoryId = :categoryId AND isDeleted = 0 ORDER BY date DESC")
    fun getExpensesByDateRangeAndCategory(startDate: Date, endDate: Date, categoryId: String): Flow<List<ExpenseEntity>>

    @Query("SELECT SUM(amount) FROM expenses WHERE isDeleted = 0")
    fun getTotalExpenses(): Flow<Double?>

    @Query("SELECT SUM(amount) FROM expenses WHERE date BETWEEN :startDate AND :endDate AND isDeleted = 0")
    fun getTotalExpensesByDateRange(startDate: Date, endDate: Date): Flow<Double?>

    @Query("SELECT SUM(amount) FROM expenses WHERE categoryId = :categoryId AND isDeleted = 0")
    fun getTotalExpensesByCategory(categoryId: String): Flow<Double?>

    @Query("SELECT SUM(amount) FROM expenses WHERE categoryId = :categoryId AND date BETWEEN :startDate AND :endDate AND isDeleted = 0")
    fun getTotalExpensesByCategoryAndDateRange(categoryId: String, startDate: Date, endDate: Date): Flow<Double?>

    @Query("UPDATE expenses SET isDeleted = 1 WHERE id = :id")
    suspend fun softDeleteExpense(id: String)

    @Query("SELECT * FROM expenses WHERE paymentMethod = :method AND date BETWEEN :startDate AND :endDate AND isDeleted = 0")
    fun getExpensesByPaymentMethod(method: String, startDate: Date, endDate: Date): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expenses WHERE tags LIKE :tag AND isDeleted = 0 ORDER BY date DESC")
    fun getExpensesByTag(tag: String): Flow<List<ExpenseEntity>>
}