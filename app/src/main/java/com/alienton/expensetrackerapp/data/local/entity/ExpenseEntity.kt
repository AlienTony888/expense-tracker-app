package com.alienton.expensetrackerapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ExpenseEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val amount: Double,
    val description: String,
    val categoryId: String,
    val date: Date = Date(),
    val tags: String = "",
    val receipt: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val isRecurring: Boolean = false,
    val recurringInterval: String? = null,
    val reminderEnabled: Boolean = false,
    val reminderTime: Long? = null,
    val paymentMethod: String = "CASH",
    val isDeleted: Boolean = false
)