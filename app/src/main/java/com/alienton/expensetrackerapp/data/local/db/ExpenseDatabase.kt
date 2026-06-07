package com.alienton.expensetrackerapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alienton.expensetrackerapp.data.local.dao.ExpenseDao
import com.alienton.expensetrackerapp.data.local.dao.CategoryDao
import com.alienton.expensetrackerapp.data.local.dao.BudgetDao
import com.alienton.expensetrackerapp.data.local.entity.ExpenseEntity
import com.alienton.expensetrackerapp.data.local.entity.CategoryEntity
import com.alienton.expensetrackerapp.data.local.entity.BudgetEntity

@Database(
    entities = [
        ExpenseEntity::class,
        CategoryEntity::class,
        BudgetEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTimeConverters::class)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao
    abstract fun budgetDao(): BudgetDao
}