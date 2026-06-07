package com.alienton.expensetrackerapp.domain.usecase

import com.alienton.expensetrackerapp.data.repository.CategoryRepository
import com.alienton.expensetrackerapp.data.local.entity.CategoryEntity
import com.alienton.expensetrackerapp.domain.model.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<Result<List<CategoryEntity>>> = categoryRepository.getAllCategories()
}