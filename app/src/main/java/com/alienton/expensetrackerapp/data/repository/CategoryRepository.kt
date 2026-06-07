package com.alienton.expensetrackerapp.data.repository

import com.alienton.expensetrackerapp.data.local.dao.CategoryDao
import com.alienton.expensetrackerapp.data.local.entity.CategoryEntity
import com.alienton.expensetrackerapp.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
) {
    suspend fun addCategory(category: CategoryEntity): Result<Unit> = try {
        categoryDao.insertCategory(category)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error("Failed to add category: ${e.message}")
    }

    suspend fun updateCategory(category: CategoryEntity): Result<Unit> = try {
        categoryDao.updateCategory(category)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error("Failed to update category: ${e.message}")
    }

    suspend fun deleteCategory(category: CategoryEntity): Result<Unit> = try {
        categoryDao.deleteCategory(category)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error("Failed to delete category: ${e.message}")
    }

    fun getAllCategories(): Flow<Result<List<CategoryEntity>>> = categoryDao.getAllCategories()
        .map { Result.Success(it) }
        .catch { emit(Result.Error("Failed to fetch categories: ${it.message}")) }

    suspend fun initializeDefaultCategories(): Result<Unit> = try {
        val defaultCategories = listOf(
            CategoryEntity("1", "Food & Dining", "🍽️", "#FF6B6B", true),
            CategoryEntity("2", "Transportation", "🚗", "#4ECDC4", true),
            CategoryEntity("3", "Entertainment", "🎬", "#45B7D1", true),
            CategoryEntity("4", "Utilities", "💡", "#FFA07A", true),
            CategoryEntity("5", "Healthcare", "⚕️", "#98D8C8", true),
            CategoryEntity("6", "Shopping", "🛍️", "#F7DC6F", true),
            CategoryEntity("7", "Other", "📌", "#BB8FCE", true)
        )
        categoryDao.insertCategories(defaultCategories)
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error("Failed to initialize categories: ${e.message}")
    }
}