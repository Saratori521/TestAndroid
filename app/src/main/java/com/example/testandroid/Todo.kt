package com.example.testandroid

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "todos")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String = "",
    val isCompleted: Boolean = false,
    val createdAt: Date = Date(),
    val priority: Priority = Priority.MEDIUM
)

enum class Priority {
    LOW, MEDIUM, HIGH
} 