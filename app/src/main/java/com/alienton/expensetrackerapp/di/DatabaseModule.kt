package com.alienton.expensetrackerapp.di

import android.content.Context
import androidx.room.Room
import com.alienton.expensetrackerapp.data.local.db.ExpenseDatabase
import com.alienton.expensetrackerapp.data.local.dao.ExpenseDao
import com.alienton.expensetrackerapp.data.local.dao.CategoryDao
import com.alienton.expensetrackerapp.data.local.dao.BudgetDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideExpenseDatabase(
        @ApplicationContext context: Context
    ): ExpenseDatabase = Room.databaseBuilder(
        context,
        ExpenseDatabase::class.java,
        "expense_tracker_db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideExpenseDao(database: ExpenseDatabase): ExpenseDao = database.expenseDao()

    @Singleton
    @Provides
    fun provideCategoryDao(database: ExpenseDatabase): CategoryDao = database.categoryDao()

    @Singleton
    @Provides
    fun provideBudgetDao(database: ExpenseDatabase): BudgetDao = database.budgetDao()
}